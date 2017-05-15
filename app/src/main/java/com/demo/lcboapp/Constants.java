package com.demo.lcboapp;

import android.app.AlarmManager;

public interface Constants {

	int DRAWER_ID_STORES = 1;
	int DRAWER_ID_PRODUCTS_BY_CATEGORIES = 2;
	int DRAWER_ID_PRODUCTS_SEARCH_WITH_OPTIONS = 3;
	int DRAWER_ID_SHOPPING_CART = 4;
	int DRAWER_ID_ABOUT = 5;

	String BASE_URL = "https://lcboapi.com";
	String ACCESS_KEY = "MDphZjEzMDQzNC0zMTZkLTExZTctYmMxMi0xNzgyZmRlODRjZGQ6VmdXNG9BQjBLZnd1Z0NNR1RLMkRTV2hwQVRxZmdaS1EyNjJI";
	int DEFAULT_ITEMS_COUNT = 20;

	String EXTRA_STORE = "extra_store";
	String EXTRA_STORE_NAME = "extra_store_name";
	String EXTRA_STORE_LATITUDE = "extra_store_latitude";
	String EXTRA_STORE_LONGITUDE = "extra_store_longitude";
	String EXTRA_STORE_ADDRESS = "extra_store_address";
	String EXTRA_PRODUCT_CATEGORY = "extra_product_category";

	long CLEAR_DATABASE_INTERVAL = AlarmManager.INTERVAL_DAY;

}