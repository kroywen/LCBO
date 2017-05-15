package com.demo.lcboapp.ui.product.search.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.lcboapp.R;
import com.demo.lcboapp.data.event.AddToCartEvent;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.ui.product.details.view.ProductDetailsDialog;
import com.demo.lcboapp.ui.product.list.view.OnProductClickListener;
import com.demo.lcboapp.ui.product.list.view.ProductsListAdapter;
import com.demo.lcboapp.ui.product.search.presenter.ProductsSearchPresenter;
import com.demo.lcboapp.ui.shoppingcart.model.ShoppingCart;
import com.demo.lcboapp.domain.util.Utils;
import com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsSearchFragment extends Fragment implements
		ProductsSearchView, EndlessRecyclerViewAdapter.RequestToLoadMoreListener, OnProductClickListener
{

	@BindView(R.id.productsListView) RecyclerView mProductsListView;

	private ProductsSearchPresenter mPresenter;
	private ProductsListAdapter mAdapter;
	private EndlessRecyclerViewAdapter mEndlessAdapter;
	private int page = 1;
	private Integer[] mSelectedIndices = null;
	private String[] mFilterParams;
	private String mSearchQuery;

	public static ProductsSearchFragment newInstance() {
		return new ProductsSearchFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		mFilterParams = getResources().getStringArray(R.array.products_filter_params);
		mPresenter = new ProductsSearchPresenter();
		mAdapter = new ProductsListAdapter(getActivity(), this);
		mEndlessAdapter = new EndlessRecyclerViewAdapter(getActivity(), mAdapter, this);
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
		searchProducts();
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

	private void searchProducts() {
		String where = Utils.getSelectedItems(mFilterParams, mSelectedIndices);
		mPresenter.searchProducts(page, where, mSearchQuery);
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
		if (page > 1) searchProducts();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getMenuInflater().inflate(R.menu.search_menu, menu);
		MenuItem item = menu.findItem(R.id.action_search);
		SearchView mSearchView = (SearchView) item.getActionView();
		mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				item.collapseActionView();
				mSearchQuery = query;
				reset();
				searchProducts();
				return false;
			}
			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.action_filter:
				showFilterDialog();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void showFilterDialog() {
		new MaterialDialog.Builder(getActivity())
				.title(R.string.filter)
				.items(R.array.products_filter_text)
				.itemsCallbackMultiChoice(mSelectedIndices, (dialog, which, text) -> applyFilter(which))
				.positiveText(R.string.select)
				.negativeText(R.string.cancel)
				.show();
	}

	private boolean applyFilter(Integer[] selectedIndices) {
		boolean filterChanged = !Arrays.equals(mSelectedIndices, selectedIndices);
		if (filterChanged) {
			mSelectedIndices = selectedIndices;
			reset();
			searchProducts();
		}
		return false;
	}

	private void reset() {
		page = 1;
		mAdapter.clear();
		mEndlessAdapter.restartAppending();
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