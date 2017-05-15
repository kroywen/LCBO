package com.demo.lcboapp.data.event;

import com.demo.lcboapp.data.model.Product;

public class AddToCartEvent {

	public final Product product;
	public final int count;

	public AddToCartEvent(Product product, int count) {
		this.product = product;
		this.count = count;
	}

}