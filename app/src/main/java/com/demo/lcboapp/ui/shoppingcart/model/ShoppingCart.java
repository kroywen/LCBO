package com.demo.lcboapp.ui.shoppingcart.model;

import android.content.Context;

import com.demo.lcboapp.LCBOApp;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.domain.util.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShoppingCart {

	private static final String CACHE_FILENAME = "lcbo_shopping_cart";

	private static ShoppingCart mInstance;
	private Map<Product, Integer> mItems;

	private ShoppingCart() {
		init();
	}

	public static ShoppingCart getInstance() {
		if (mInstance == null) {
			mInstance = new ShoppingCart();
		}
		return mInstance;
	}

	private void init() {
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = LCBOApp.getInstance().openFileInput(CACHE_FILENAME);
			ois = new ObjectInputStream(fis);
			mItems = (Map<Product, Integer>) ois.readObject();
		} catch(ClassNotFoundException | IOException | ClassCastException e) {
			e.printStackTrace();
			mItems = new HashMap<>();
		} finally {
			try {
				if (ois != null) ois.close();
			} catch (IOException e) {}
		}
	}

	public boolean save() {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = LCBOApp.getInstance()
					.openFileOutput(CACHE_FILENAME, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(mItems);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (oos != null) oos.close();
			} catch (IOException e) {}
		}
	}

	public Map<Product, Integer> getItems() {
		return mItems;
	}

	public void add(Product product, int count) {
		checkItemsNotNull();
		int total = 0;
		if (mItems.containsKey(product))
			total = mItems.get(product);
		mItems.put(product, total + count);
		save();
	}

	public void update(Product product, int count) {
		checkItemsNotNull();
		mItems.put(product, count);
		save();
	}

	public void remove(Product product) {
		checkItemsNotNull();
		mItems.remove(product);
		save();
	}

	public int size() {
		checkItemsNotNull();
		return mItems.size();
	}

	public int getTotalPrice() {
		checkItemsNotNull();
		int total = 0;
		Set<Product> keys = mItems.keySet();
		if (Utils.isNullOrEmpty(keys)) return 0;
		for (Product product : keys)
			total += product.getPriceInCents() * mItems.get(product);
		return total;
	}

	private void checkItemsNotNull() {
		if (mItems == null)
			mItems = new HashMap<>();
	}

}