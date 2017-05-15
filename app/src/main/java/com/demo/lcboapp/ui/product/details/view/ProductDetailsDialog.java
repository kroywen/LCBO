package com.demo.lcboapp.ui.product.details.view;

import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.demo.lcboapp.R;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.ui.product.list.view.OnProductClickListener;
import com.demo.lcboapp.domain.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsDialog extends DialogFragment {

	public static final String TAG = ProductDetailsDialog.class.getSimpleName();

	@BindView(R.id.productName) TextView mNameView;
	@BindView(R.id.productImage) ImageView mImageView;
	@BindView(R.id.productNewPrice) TextView mNewPriceView;
	@BindView(R.id.productOldPrice) TextView mOldPriceView;
	@BindView(R.id.productCategory) TextView mCategoryView;
	@BindView(R.id.productPackage) TextView mPackageView;
	@BindView(R.id.productAlcohol) TextView mAlcoholView;
	@BindView(R.id.productProducer) TextView mProducerView;

	private Product mProduct;
	private OnProductClickListener mListener;

	public static void show(FragmentManager fm, Product product, OnProductClickListener listener) {
		ProductDetailsDialog dialog = new ProductDetailsDialog();
		dialog.setRetainInstance(true);
		dialog.setProduct(product);
		dialog.setOnProductClickListener(listener);
		dialog.show(fm, TAG);
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity())
				.inflate(R.layout.product_details, null, false);
		ButterKnife.bind(this, view);
		updateViews();
		return new MaterialDialog.Builder(getActivity())
				.customView(view, true)
				.build();
	}

	private void updateViews() {
		if (mProduct != null) {
			mNameView.setText(mProduct.getName());

			Glide.with(getContext())
					.load(mProduct.getImageThumbUrl())
					.into(mImageView);

			mNewPriceView.setText(Utils.getFormattedPrice(mProduct.getPriceInCents()));

			mOldPriceView.setText(Utils.getFormattedPrice(mProduct.getRegularPriceInCents()));
			mOldPriceView.setPaintFlags(mOldPriceView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			mOldPriceView.setVisibility(mProduct.hasNewPrice() ? View.VISIBLE : View.GONE);

			mCategoryView.setText(mProduct.getCategory());
			mPackageView.setText(mProduct.getPacage());
			mAlcoholView.setText(mProduct.getAlcoholContentFormatted());
			mProducerView.setText(mProduct.getProducer());
		}
	}

	public void setProduct(Product product) {
		mProduct = product;
	}

	public void setOnProductClickListener(OnProductClickListener listener) {
		mListener = listener;
	}

	@OnClick(R.id.productAdd)
	public void onProductAddClicked() {
		if (mListener != null)
			mListener.onAddToCartClicked(mProduct);
		dismiss();
	}

}