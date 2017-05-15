package com.demo.lcboapp.ui.product.list.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.lcboapp.R;
import com.demo.lcboapp.data.event.AddToCartEvent;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.ui.product.details.view.ProductDetailsDialog;
import com.demo.lcboapp.ui.product.list.presenter.ProductsListPresenter;
import com.demo.lcboapp.ui.shoppingcart.model.ShoppingCart;
import com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.demo.lcboapp.Constants.EXTRA_PRODUCT_CATEGORY;

public class ProductsListFragment extends Fragment implements ProductsListView,
		EndlessRecyclerViewAdapter.RequestToLoadMoreListener, OnProductClickListener
{

	@BindView(R.id.productsListView) RecyclerView mProductsListView;

	private ProductsListPresenter mPresenter;
	private ProductsListAdapter mAdapter;
	private EndlessRecyclerViewAdapter mEndlessAdapter;
	private int page = 1;
	private String mCategory;

	public static ProductsListFragment newInstance(String category) {
		ProductsListFragment f = new ProductsListFragment();
		Bundle args = new Bundle();
		args.putString(EXTRA_PRODUCT_CATEGORY, category);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPresenter = new ProductsListPresenter();
		mAdapter = new ProductsListAdapter(getActivity(), this);
		mEndlessAdapter = new EndlessRecyclerViewAdapter(getActivity(), mAdapter, this);
		mCategory = getArguments().getString(EXTRA_PRODUCT_CATEGORY);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.products_list, container, false);
		ButterKnife.bind(this, view);
		mPresenter.attachView(this);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		initRecyclerView();
		loadProducts();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mPresenter.detachView();
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		mProductsListView.setLayoutManager(layoutManager);
		mProductsListView.setHasFixedSize(true);
		mProductsListView.addItemDecoration(
				new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
		mProductsListView.setAdapter(mEndlessAdapter);
	}

	private void loadProducts() {
		mPresenter.loadProducts(page, null, mCategory);
	}

	@Override
	public void showProductsList(List<Product> products) {
		mEndlessAdapter.onDataReady(true);
		mAdapter.addProducts(products);
		page++;
	}

	@Override
	public void showProductsEmpty() {
		mEndlessAdapter.onDataReady(false);
		if (page == 1) {
			Snackbar.make(getView(), R.string.no_products, Snackbar.LENGTH_SHORT).show();
		}
	}

	@Override
	public void showError(String error) {
		mEndlessAdapter.onDataReady(false);
		Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
	}

	@Override
	public void onLoadMoreRequested() {
		if (page > 1) loadProducts();
	}

	@Override
	public void onProductClicked(Product product) {
		ProductDetailsDialog.show(getActivity().getSupportFragmentManager(), product, this);
	}

	@Override
	public void onAddToCartClicked(Product product) {
		new MaterialDialog.Builder(getActivity())
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

}