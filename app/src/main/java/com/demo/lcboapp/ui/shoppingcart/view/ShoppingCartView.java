package com.demo.lcboapp.ui.shoppingcart.view;

import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.ui.common.MvpView;

import java.util.Map;

public interface ShoppingCartView extends MvpView {

	void showItems(Map<Product, Integer> items, int totalPrice);

	void showEmpty();

}