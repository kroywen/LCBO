package com.demo.lcboapp.ui.product.list.view;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private Context mContext;
	private OnProductClickListener mListener;
	private List<Product> mProducts;

	public ProductsListAdapter(Context context, OnProductClickListener listener) {
		mContext = context;
		mListener = listener;
		mProducts = new ArrayList<>();
	}

	public void addProducts(List<Product> products) {
		int count = getItemCount();
		mProducts.addAll(products);
		notifyItemRangeInserted(count + 1, products.size());
	}

	public void clear() {
		mProducts.clear();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
		return new ProductViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof ProductViewHolder) {
			ProductViewHolder productViewHolder = (ProductViewHolder) holder;
			Product product = mProducts.get(position);

			productViewHolder.name.setText(product.getName());

			Glide.with(mContext)
					.load(product.getImageThumbUrl())
					.bitmapTransform(new CropCircleTransformation(mContext))
					.into(productViewHolder.icon);

			productViewHolder.itemView.setOnClickListener(v -> {
				if (mListener != null)
					mListener.onProductClicked(product);
			});

			productViewHolder.add.setOnClickListener(v -> {
				if (mListener != null)
					mListener.onAddToCartClicked(product);
			});
		}
	}

	@Override
	public int getItemCount() {
		return (mProducts != null) ? mProducts.size() : 0;
	}

	static class ProductViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.productName) TextView name;
		@BindView(R.id.productIcon) ImageView icon;
		@BindView(R.id.productAdd) ImageView add;

		public ProductViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}

	}

}