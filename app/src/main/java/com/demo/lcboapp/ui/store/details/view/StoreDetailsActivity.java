package com.demo.lcboapp.ui.store.details.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.lcboapp.R;
import com.demo.lcboapp.data.event.AddToCartEvent;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.data.model.Store;
import com.demo.lcboapp.ui.product.details.view.ProductDetailsDialog;
import com.demo.lcboapp.ui.product.list.view.OnProductClickListener;
import com.demo.lcboapp.ui.product.list.view.ProductsListAdapter;
import com.demo.lcboapp.ui.shoppingcart.model.ShoppingCart;
import com.demo.lcboapp.ui.store.details.presenter.StoreDetailsPresenter;
import com.demo.lcboapp.ui.store.location.view.StoreLocationActivity;
import com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.parceler.Parcels;

import java.util.List;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.demo.lcboapp.Constants.EXTRA_STORE;

public class StoreDetailsActivity extends AppCompatActivity implements
		StoreDetailsView, EndlessRecyclerViewAdapter.RequestToLoadMoreListener, OnProductClickListener
{

	@BindView(R.id.rootView) View mRootView;
	@BindView(R.id.toolbar) Toolbar mToolbar;
	@BindView(R.id.contentView) View mContentView;
	@BindView(R.id.progressView) View mProgressView;
	@BindView(R.id.storeName) TextView mNameView;
	@BindView(R.id.storeLocationLayout) View mLocationLayout;
	@BindView(R.id.storeLocation) TextView mLocationView;
	@BindView(R.id.storePhoneLayout) View mPhoneLayout;
	@BindView(R.id.storePhone) TextView mPhoneView;
	@BindView(R.id.storeFaxLayout) View mFaxLayout;
	@BindView(R.id.storeFax) TextView mFaxView;
	@BindView(R.id.storeDates) StoreDateView mDatesView;
	@BindView(R.id.storeProducts) TextView mStoreProductsView;
	@BindView(R.id.productsListView) RecyclerView mProductsListView;

	private Store mStore;

	private StoreDetailsPresenter mPresenter;
	private ProductsListAdapter mAdapter;
	private EndlessRecyclerViewAdapter mEndlessAdapter;
	private int page = 1;

	public static void show(Context context, Store store) {
		Intent intent = new Intent(context, StoreDetailsActivity.class);
		Bundle extras = new Bundle();
		extras.putParcelable(EXTRA_STORE, Parcels.wrap(store));
		intent.putExtras(extras);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_details);
		ButterKnife.bind(this);

		mPresenter = new StoreDetailsPresenter();
		mPresenter.attachView(this);

		mStore = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_STORE));
		if (mStore == null) {
			finish();
			return;
		}
		updateStoreViews();

		initRecyclerView();
		initActionBar();

		loadStoreProducts();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mPresenter.detachView();
	}

	private void initRecyclerView() {
		mAdapter = new ProductsListAdapter(this, this);
		mEndlessAdapter = new EndlessRecyclerViewAdapter(this, mAdapter, this);

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		mProductsListView.setLayoutManager(layoutManager);
		mProductsListView.setHasFixedSize(true);
		mProductsListView.addItemDecoration(
				new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
		mProductsListView.setNestedScrollingEnabled(false);
		mProductsListView.setAdapter(mEndlessAdapter);
	}

	private void initActionBar() {
		setSupportActionBar(mToolbar);
		getSupportActionBar().setTitle(mStore.getName());
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void loadStoreProducts() {
		mPresenter.loadStoreProducts(mStore.getId(), page);
	}

	@OnClick(R.id.storePhoneLayout)
	public void onPhoneClicked() {
		if (mStore == null || !mStore.hasTelephone())
			return;
		String[] phones = mStore.getTelephone().split(",");
		if (phones.length == 1) {
			dialPhone(phones[0]);
		} else {
			showSelectPhoneDialog(phones);
		}
	}

	private void showSelectPhoneDialog(String[] phones) {
		new MaterialDialog.Builder(this)
				.title(R.string.select_phone)
				.items(phones)
				.itemsCallbackSingleChoice(-1, (dialog, view, which, text) -> {
					dialPhone(phones[which]);
					return true;
				})
				.show();
	}

	private void dialPhone(String phone) {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		String tel = "tel:" + phone;
		intent.setData(Uri.parse(tel));
		startActivity(intent);
	}

	@OnClick(R.id.storeLocationLayout)
	public void onLocationClicked() {
		if (mStore == null) return;
		StoreLocationActivity.show(this, mStore);
	}

	@Override
	public void showStoreProducts(Store store, List<Product> products) {
		mStore = store;
		updateStoreViews();

		mEndlessAdapter.onDataReady(true);
		mAdapter.addProducts(products);
		page++;
	}

	@Override
	public void showStoreProductsEmpty(Store store) {
		mStore = store;
		updateStoreViews();
		mEndlessAdapter.onDataReady(false);
		Snackbar.make(mRootView, R.string.no_products, Snackbar.LENGTH_SHORT).show();
	}

	@Override
	public void showError(String error) {
		mEndlessAdapter.onDataReady(false);
		Snackbar.make(mRootView, error, Snackbar.LENGTH_SHORT).show();
	}

	private void updateStoreViews() {
		if (mStore != null) {
			mNameView.setText(mStore.getName());
			if (mStore.hasLocation()) {
				mLocationLayout.setVisibility(View.VISIBLE);
				mLocationView.setText(mStore.getLocation());
			} else {
				mLocationLayout.setVisibility(View.GONE);
			}
			if (mStore.hasTelephone()) {
				mPhoneLayout.setVisibility(View.VISIBLE);
				mPhoneView.setText(mStore.getTelephone());
			} else {
				mPhoneLayout.setVisibility(View.INVISIBLE);
			}
			if (mStore.hasFax()) {
				mFaxLayout.setVisibility(View.VISIBLE);
				mFaxView.setText(mStore.getFax());
			} else {
				mFaxLayout.setVisibility(View.INVISIBLE);
			}

			mDatesView.updateDates(mStore);

			mStoreProductsView.setText(getString(R.string.store_products_count,
					mStore.getProductsCount()));
		}
	}

	@Override
	public void onProductClicked(Product product) {
		ProductDetailsDialog.show(getSupportFragmentManager(), product, this);
	}

	@Override
	public void onAddToCartClicked(Product product) {
		new MaterialDialog.Builder(this)
				.title(R.string.select_count)
				.customView(R.layout.dialog_number_picker, false)
				.positiveText(R.string.add)
				.onPositive((dialog, which) -> {
					int count = ((MaterialNumberPicker) dialog.getCustomView()).getValue();
					ShoppingCart.getInstance().add(product, count);
					EventBus.getDefault().post(new AddToCartEvent(product, count));
				})
				.negativeText(R.string.cancel)
				.show();
	}

	@Override
	public void onLoadMoreRequested() {
		// FIXME bug: onLoadMoreRequested is called continuously
		// if (page > 1) loadStoreProducts();
	}

	@Override
	public void showProgress() {
		mProgressView.setVisibility(View.VISIBLE);
		mContentView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void hideProgress() {
		mProgressView.setVisibility(View.INVISIBLE);
		mContentView.setVisibility(View.VISIBLE);
	}

}