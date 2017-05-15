package com.demo.lcboapp.ui.store.details.view;

import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.data.model.Store;
import com.demo.lcboapp.ui.common.MvpView;

import java.util.List;

public interface StoreDetailsView extends MvpView {

	void showStoreProducts(Store store, List<Product> products);

	void showStoreProductsEmpty(Store store);

	void showError(String error);

	void showProgress();

	void hideProgress();

}