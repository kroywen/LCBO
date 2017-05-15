package com.demo.lcboapp.ui.product.list.view;

import com.demo.lcboapp.data.model.Product;

public interface OnProductClickListener {

	void onProductClicked(Product product);

	void onAddToCartClicked(Product product);

}