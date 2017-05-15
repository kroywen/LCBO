package com.demo.lcboapp.ui.product.list.presenter;

import com.demo.lcboapp.LCBOApp;
import com.demo.lcboapp.domain.cache.DatabaseHelper;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.domain.network.LCBOApiService;
import com.demo.lcboapp.domain.network.model.GetProductsListResponse;
import com.demo.lcboapp.ui.common.BaseMvpPresenter;
import com.demo.lcboapp.ui.product.list.view.ProductsListView;
import com.demo.lcboapp.domain.util.Utils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.demo.lcboapp.Constants.DEFAULT_ITEMS_COUNT;
import static java.net.HttpURLConnection.HTTP_OK;

public class ProductsListPresenter extends BaseMvpPresenter<ProductsListView> {

	private Disposable mDisbosable;
	private LCBOApiService mApiService;
	private DatabaseHelper mDatabaseHelper;

	public ProductsListPresenter() {
		mApiService = LCBOApp.getInstance().getApiService();
		mDatabaseHelper = DatabaseHelper.getInstance();
	}

	@Override
	public void detachView() {
		super.detachView();
		Utils.unsubscribe(mDisbosable);
	}

	public void loadProducts(int page, String where, String query) {
		checkViewAttached();
		Utils.unsubscribe(mDisbosable);

		if (Utils.isNetworkConnected(LCBOApp.getInstance())) {
			mDisbosable = mApiService.getProductsList(page, DEFAULT_ITEMS_COUNT, where, query)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.doOnNext(response -> {
						if (response != null)
							mDatabaseHelper.saveProducts(response.getResult());
					})
					.subscribeWith(createObserver());
		} else {
			mDisbosable = mDatabaseHelper.getProductsList(page, DEFAULT_ITEMS_COUNT, where, query)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribeWith(createObserver());
		}
	}

	private DisposableObserver<GetProductsListResponse> createObserver() {
		return new DisposableObserver<GetProductsListResponse>() {

			@Override
			public void onNext(GetProductsListResponse response) {
				int status = response.getStatus();
				if (status == HTTP_OK) {
					List<Product> products = response.getResult();
					if (!Utils.isNullOrEmpty(products)) {
						getView().showProductsList(products);
					} else {
						getView().showProductsEmpty();
					}
				} else {
					String error = response.getMessage();
					getView().showError(error);
				}
			}

			@Override
			public void onComplete() {}

			@Override
			public void onError(Throwable e) {
				getView().showError(e.getMessage());
			}

		};
	}

}