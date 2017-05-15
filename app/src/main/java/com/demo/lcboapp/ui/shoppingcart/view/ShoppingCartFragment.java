package com.demo.lcboapp.ui.shoppingcart.view;

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
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.lcboapp.R;
import com.demo.lcboapp.data.event.AddToCartEvent;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.ui.shoppingcart.presenter.ShoppingCartPresenter;
import com.demo.lcboapp.domain.util.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCartFragment extends Fragment implements ShoppingCartView, OnEditItemClickListener
{

	@BindView(R.id.cartTotalPrice) TextView mTotalPriceView;
	@BindView(R.id.cartListView) RecyclerView mCartListView;

	private ShoppingCartPresenter mPresenter;
	private ShoppingCartAdapter mAdapter;

	public static ShoppingCartFragment newInstance() {
		return new ShoppingCartFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPresenter = new ShoppingCartPresenter();
		mAdapter = new ShoppingCartAdapter(getActivity(), this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.shopping_cart, container, false);
		ButterKnife.bind(this, view);
		mPresenter.attachView(this);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		initRecyclerView();
		loadItems();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mPresenter.detachView();
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		mCartListView.setLayoutManager(layoutManager);
		mCartListView.setHasFixedSize(true);
		mCartListView.addItemDecoration(
				new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
		mCartListView.setAdapter(mAdapter);
	}

	private void loadItems() {
		mPresenter.loadItems();
	}

	@Override
	public void showItems(Map<Product, Integer> items, int totalPrice) {
		mAdapter.setItems(items);
		mTotalPriceView.setText(getString(R.string.total,
				Utils.getFormattedPrice(totalPrice)));
	}

	@Override
	public void showEmpty() {
		mAdapter.setItems(null);
		mTotalPriceView.setText(getString(R.string.total,
				Utils.getFormattedPrice(0)));
		Snackbar.make(getView(), R.string.no_items, Snackbar.LENGTH_SHORT).show();
	}

	@Override
	public void onEditItemClicked(Product product, int count) {
		MaterialNumberPicker picker = (MaterialNumberPicker)
				LayoutInflater.from(getActivity()).inflate(R.layout.dialog_number_picker, null, false);
		picker.setMinValue(0);
		picker.setValue(count);

		new MaterialDialog.Builder(getActivity())
				.title(R.string.select_count)
				.customView(picker, false)
				.positiveText(R.string.add)
				.onPositive((dialog, which) -> {
					int newCount = ((MaterialNumberPicker) dialog.getCustomView()).getValue();
					if (newCount > 0) {
						mPresenter.updateItem(product, newCount);
					} else {
						mPresenter.removeItem(product);
					}
					EventBus.getDefault().post(new AddToCartEvent(product, newCount));
				})
				.negativeText(R.string.cancel)
				.show();
	}

}