package com.demo.lcboapp.ui.shoppingcart.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.lcboapp.R;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.domain.util.CropCircleTransformation;
import com.demo.lcboapp.domain.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ItemViewHolder> {

	private Context mContext;
	private OnEditItemClickListener mListener;
	private Map<Product, Integer> mItems;
	private List<Product> mKeys;

	public ShoppingCartAdapter(Context context, OnEditItemClickListener listener) {
		mContext = context;
		mListener = listener;
		mItems = new HashMap<>();
		mKeys = new ArrayList<>();
	}

	public void setItems(Map<Product, Integer> items) {
		mItems = !Utils.isNullOrEmpty(items) ? items : new HashMap<>();
		mKeys = new ArrayList<>(mItems.keySet());
		notifyDataSetChanged();
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ItemViewHolder holder, int position) {
		Product product = mKeys.get(position);
		int count = mItems.get(product);

		holder.name.setText(product.getName());

		Glide.with(mContext)
				.load(product.getImageThumbUrl())
				.bitmapTransform(new CropCircleTransformation(mContext))
				.into(holder.icon);

		holder.count.setText(mContext.getString(R.string.count, count));
		holder.price.setText(mContext.getString(R.string.price,
				Utils.getFormattedPrice(product.getPriceInCents() * count)));

		holder.edit.setOnClickListener(v -> {
			if (mListener != null)
				mListener.onEditItemClicked(product, count);
		});
	}

	@Override
	public int getItemCount() {
		return (mItems != null) ? mItems.size() : 0;
	}

	static class ItemViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.itemName) TextView name;
		@BindView(R.id.itemIcon) ImageView icon;
		@BindView(R.id.itemCount) TextView count;
		@BindView(R.id.itemPrice) TextView price;
		@BindView(R.id.itemEdit) ImageView edit;

		public ItemViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}

	}

}