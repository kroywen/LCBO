package com.demo.lcboapp.ui.product.search.view;

import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.ui.common.MvpView;

import java.util.List;

public interface ProductsSearchView extends MvpView {

	void showProductsList(List<Product> products);

	void showProductsEmpty();

	void showError(String error);

}