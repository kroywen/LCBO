package com.demo.lcboapp.ui.store.list.view;

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
import com.demo.lcboapp.data.model.Store;
import com.demo.lcboapp.ui.store.details.view.StoreDetailsActivity;
import com.demo.lcboapp.ui.store.list.presenter.StoresListPresenter;
import com.demo.lcboapp.domain.util.Utils;
import com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoresListFragment extends Fragment implements
		StoresListView, EndlessRecyclerViewAdapter.RequestToLoadMoreListener, OnStoreClickListener
{

	@BindView(R.id.storesListView) RecyclerView mStoresListView;

	private StoresListPresenter mPresenter;
	private StoresListAdapter mAdapter;
	private EndlessRecyclerViewAdapter mEndlessAdapter;
	private int page = 1;
	private Integer[] mSelectedIndices = null;
	private String[] mFilterParams;
	private String mSearchQuery;

	public static StoresListFragment newInstance() {
		return new StoresListFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		mFilterParams = getResources().getStringArray(R.array.stores_filter_params);
		mPresenter = new StoresListPresenter();
		mAdapter = new StoresListAdapter(this);
		mEndlessAdapter = new EndlessRecyclerViewAdapter(getActivity(), mAdapter, this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.stores_list, container, false);
		ButterKnife.bind(this, view);
		mPresenter.attachView(this);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		initRecyclerView();
		loadStores();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mPresenter.detachView();
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		mStoresListView.setLayoutManager(layoutManager);
		mStoresListView.setHasFixedSize(true);
		mStoresListView.addItemDecoration(
				new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
		mStoresListView.setAdapter(mEndlessAdapter);
	}

	private void loadStores() {
		String where = Utils.getSelectedItems(mFilterParams, mSelectedIndices);
		mPresenter.loadStores(page, where, mSearchQuery);
	}

	@Override
	public void showStoresList(List<Store> stores) {
		mEndlessAdapter.onDataReady(true);
		mAdapter.addStores(stores);
		page++;
	}

	@Override
	public void showStoresEmpty() {
		mEndlessAdapter.onDataReady(false);
		if (page == 1) {
			Snackbar.make(getView(), R.string.no_stores, Snackbar.LENGTH_SHORT).show();
		}
	}

	@Override
	public void showError(String error) {
		mEndlessAdapter.onDataReady(false);
		Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
	}

	@Override
	public void onLoadMoreRequested() {
		if (page > 1) loadStores();
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
				loadStores();
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
				.items(R.array.stores_filter_text)
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
			loadStores();
		}
		return false;
	}

	private void reset() {
		page = 1;
		mAdapter.clear();
		mEndlessAdapter.restartAppending();
	}

	@Override
	public void onStoreClicked(Store store) {
		StoreDetailsActivity.show(getActivity(), store);
	}

}