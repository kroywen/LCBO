package com.demo.lcboapp.ui.store.details.presenter;

import com.demo.lcboapp.LCBOApp;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.data.model.Store;
import com.demo.lcboapp.domain.cache.DatabaseHelper;
import com.demo.lcboapp.domain.network.LCBOApiService;
import com.demo.lcboapp.domain.network.model.GetStoreProductsListResponse;
import com.demo.lcboapp.domain.util.Utils;
import com.demo.lcboapp.ui.common.BaseMvpPresenter;
import com.demo.lcboapp.ui.store.details.view.StoreDetailsView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.demo.lcboapp.Constants.DEFAULT_ITEMS_COUNT;
import static java.net.HttpURLConnection.HTTP_OK;

public class StoreDetailsPresenter extends BaseMvpPresenter<StoreDetailsView> {

	private Disposable mDisbosable;
	private LCBOApiService mApiService;
	private DatabaseHelper mDatabaseHelper;

	public StoreDetailsPresenter() {
		mApiService = LCBOApp.getInstance().getApiService();
		mDatabaseHelper = DatabaseHelper.getInstance();
	}

	@Override
	public void detachView() {
		super.detachView();
		Utils.unsubscribe(mDisbosable);
	}

	public void loadStoreProducts(int storeId, int page) {
		checkViewAttached();
		Utils.unsubscribe(mDisbosable);

		getView().showProgress();
		if (Utils.isNetworkConnected(LCBOApp.getInstance())) {
			mDisbosable = mApiService.getStoreProductsList(storeId, page, DEFAULT_ITEMS_COUNT)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.doOnNext(response -> {
						if (response != null)
							mDatabaseHelper.saveProducts(response.getResult(), storeId);
					})
					.subscribeWith(createObserver());
		} else {
			mDisbosable = mDatabaseHelper.getStoreProductsList(storeId, page, DEFAULT_ITEMS_COUNT)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribeWith(createObserver());
		}
	}

	private DisposableObserver<GetStoreProductsListResponse> createObserver() {
		return new DisposableObserver<GetStoreProductsListResponse>() {

			@Override
			public void onNext(GetStoreProductsListResponse response) {
				getView().hideProgress();
				int status = response.getStatus();
				if (status == HTTP_OK) {
					Store store = response.getStore();
					List<Product> products = response.getResult();
					if (!Utils.isNullOrEmpty(products)) {
						getView().showStoreProducts(store, products);
					} else {
						getView().showStoreProductsEmpty(store);
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
				getView().hideProgress();
				getView().showError(e.getMessage());
			}

		};
	}

}