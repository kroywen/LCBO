package com.demo.lcboapp.domain.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.demo.lcboapp.LCBOApp;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.data.model.Store;
import com.demo.lcboapp.domain.network.model.GetProductsListResponse;
import com.demo.lcboapp.domain.network.model.GetStoreProductsListResponse;
import com.demo.lcboapp.domain.network.model.GetStoresListResponse;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import rx.schedulers.Schedulers;

public class DatabaseHelper {

	private static DatabaseHelper mInstance;
	private final BriteDatabase mDatabase;

	private DatabaseHelper() {
		DatabaseOpenHelper dbOpenHelper = new DatabaseOpenHelper(LCBOApp.getInstance());
		mDatabase = new SqlBrite.Builder().build()
				.wrapDatabaseHelper(dbOpenHelper, Schedulers.immediate());
	}

	public static DatabaseHelper getInstance() {
		if (mInstance == null)
			mInstance = new DatabaseHelper();
		return mInstance;
	}

	public Observable<GetStoresListResponse> getStoresList(int page, int count, String where, String query) {
		return Observable.fromCallable(() -> {
			GetStoresListResponse response = new GetStoresListResponse();
			List<Store> stores = new ArrayList<>();

			String sql = prepareQuery(Database.StoresTable.TABLE_NAME, page, count, where, query);
			Cursor c = mDatabase.query(sql);
			if (c != null && c.moveToFirst()) {
				do {
					stores.add(Database.StoresTable.fromCursor(c));
				} while (c.moveToNext());
				c.close();
			}

			response.setStatus(200);
			response.setResult(stores);
			return response;
		});
	}

	public Store getStoreById(int storeId) {
		String sql = "SELECT * FROM " + Database.StoresTable.TABLE_NAME + " WHERE " +
				Database.StoresTable.COLUMN_ID + "=" + storeId;
		Cursor c = mDatabase.query(sql);
		Store store = null;
		if (c != null && c.moveToFirst()) {
			store = Database.StoresTable.fromCursor(c);
			c.close();
		}
		return store;
	}

	public void saveStores(List<Store> stores) {
		BriteDatabase.Transaction transaction = mDatabase.newTransaction();
		try {
			for (Store store : stores) {
				ContentValues values = Database.StoresTable.toContentValues(store);

				int affected = mDatabase.update(Database.StoresTable.TABLE_NAME, values,
						Database.StoresTable.COLUMN_ID + "=" + store.getId());
				if (affected == 0) {
					mDatabase.insert(Database.StoresTable.TABLE_NAME, values,
							SQLiteDatabase.CONFLICT_REPLACE);
				}
			}
			transaction.markSuccessful();
		} finally {
			transaction.end();
		}
	}

	public Observable<GetProductsListResponse> getProductsList(int page, int count, String where, String query) {
		return Observable.fromCallable(() -> {
			GetProductsListResponse response = new GetProductsListResponse();
			List<Product> products = new ArrayList<>();

			String sql = prepareQuery(Database.ProductsTable.TABLE_NAME, page, count, where, query);
			Cursor c = mDatabase.query(sql);
			if (c != null && c.moveToFirst()) {
				do {
					products.add(Database.ProductsTable.fromCursor(c));
				} while (c.moveToNext());
				c.close();
			}

			response.setStatus(200);
			response.setResult(products);
			return response;
		});
	}

	public Observable<GetStoreProductsListResponse> getStoreProductsList(int storeId, int page, int count) {
		return Observable.fromCallable(() -> {
			GetStoreProductsListResponse response = new GetStoreProductsListResponse();
			Store store = getStoreById(storeId);
			List<Product> products = new ArrayList<>();

			String sql = "SELECT * FROM " + Database.ProductsTable.TABLE_NAME + " WHERE " +
					Database.ProductsTable.COLUMN_STORE_ID + "=" + storeId + " LIMIT " +
					(page-1)*count + ", " + count;

			Cursor c = mDatabase.query(sql);
			if (c != null && c.moveToFirst()) {
				do {
					products.add(Database.ProductsTable.fromCursor(c));
				} while (c.moveToNext());
				c.close();
			}

			response.setStatus(200);
			response.setStore(store);
			response.setResult(products);
			return response;
		});
	}

	public void saveProducts(List<Product> products) {
		saveProducts(products, 0);
	}

	public void saveProducts(List<Product> products, int storeId) {
		BriteDatabase.Transaction transaction = mDatabase.newTransaction();
		try {
			for (Product product : products) {
				product.setStoreId(storeId);
				ContentValues values = Database.ProductsTable.toContentValues(product);

				int affected = mDatabase.update(Database.ProductsTable.TABLE_NAME, values,
						Database.ProductsTable.COLUMN_ID + "=" + product.getId());
				if (affected == 0) {
					mDatabase.insert(Database.ProductsTable.TABLE_NAME, values,
							SQLiteDatabase.CONFLICT_REPLACE);
				}
			}
			transaction.markSuccessful();
		} finally {
			transaction.end();
		}
	}

	public void clear() {
		mDatabase.delete(Database.StoresTable.TABLE_NAME, null, null);
		mDatabase.delete(Database.ProductsTable.TABLE_NAME, null, null);
	}

	private String prepareQuery(String table, int page, int count, String where, String query) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM " + table);

		boolean hasWhere = !TextUtils.isEmpty(where),
				hasQuery = !TextUtils.isEmpty(query);
		String sqlWhere = toSqlWhere(where);
		if (hasWhere || hasQuery) {
			builder.append(" WHERE ");
			if (hasWhere && hasQuery) {
				builder.append(sqlWhere).append(" AND ").append("name LIKE '%" + query + "%'");
			} else if (hasWhere) {
				builder.append(sqlWhere);
			} else if (hasQuery) {
				builder.append("name LIKE '%" + query + "%'");
			}
		}

		int skip = (page - 1) * count;
		builder.append(" LIMIT " + skip + ", " + count);
		return builder.toString();
	}

	private String toSqlWhere(String where) {
		if (TextUtils.isEmpty(where))
			return null;

		String[] array = where.split(",");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(array[i] + "=1");
			if (i < array.length - 1)
				builder.append(" AND ");
		}
		return builder.toString();
	}

}