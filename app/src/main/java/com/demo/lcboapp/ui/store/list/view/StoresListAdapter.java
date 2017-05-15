package com.demo.lcboapp.ui.store.list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.demo.lcboapp.R;
import com.demo.lcboapp.data.model.Store;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoresListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<Store> mStores;
	private OnStoreClickListener mListener;

	public StoresListAdapter(OnStoreClickListener listener) {
		mListener = listener;
		mStores = new ArrayList<>();
	}

	public void addStores(List<Store> stores) {
		int count = getItemCount();
		mStores.addAll(stores);
		notifyItemRangeInserted(count + 1, stores.size());
	}

	public void clear() {
		mStores.clear();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item, parent, false);
		return new StoreViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof StoreViewHolder) {
			StoreViewHolder storeViewHolder = (StoreViewHolder) holder;
			Store store = mStores.get(position);

			String storeName = store.getName();
			storeViewHolder.name.setText(storeName);

			TextDrawable drawable = TextDrawable.builder()
					.buildRound(String.valueOf(storeName.charAt(0)), 0xFF3093BD);
			storeViewHolder.icon.setImageDrawable(drawable);

			storeViewHolder.itemView.setOnClickListener(v -> {
				if (mListener != null)
					mListener.onStoreClicked(store);
			});
		}
	}

	@Override
	public int getItemCount() {
		return (mStores != null) ? mStores.size() : 0;
	}

	static class StoreViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.storeName) TextView name;
		@BindView(R.id.storeIcon) ImageView icon;

		public StoreViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}

	}

}