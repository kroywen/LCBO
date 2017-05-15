package com.demo.lcboapp.ui.shoppingcart.presenter;

import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.ui.common.BaseMvpPresenter;
import com.demo.lcboapp.ui.shoppingcart.model.ShoppingCart;
import com.demo.lcboapp.ui.shoppingcart.view.ShoppingCartView;
import com.demo.lcboapp.domain.util.Utils;

import java.util.Map;

public class ShoppingCartPresenter extends BaseMvpPresenter<ShoppingCartView> {

	private ShoppingCart mShoppingCart;

	public ShoppingCartPresenter() {
		mShoppingCart = ShoppingCart.getInstance();
	}

	public void loadItems() {
		checkViewAttached();
		Map<Product, Integer> items = mShoppingCart.getItems();
		if (!Utils.isNullOrEmpty(items)) {
			int totalPrice = mShoppingCart.getTotalPrice();
			getView().showItems(items, totalPrice);
		} else {
			getView().showEmpty();
		}
	}

	public void updateItem(Product product, int count) {
		mShoppingCart.update(product, count);
		loadItems();
	}

	public void removeItem(Product product) {
		mShoppingCart.remove(product);
		loadItems();
	}

}