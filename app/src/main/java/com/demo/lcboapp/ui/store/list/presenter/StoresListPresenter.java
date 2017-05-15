package com.demo.lcboapp.ui.store.list.presenter;

import com.demo.lcboapp.LCBOApp;
import com.demo.lcboapp.domain.cache.DatabaseHelper;
import com.demo.lcboapp.data.model.Store;
import com.demo.lcboapp.domain.network.LCBOApiService;
import com.demo.lcboapp.domain.network.model.GetStoresListResponse;
import com.demo.lcboapp.ui.common.BaseMvpPresenter;
import com.demo.lcboapp.ui.store.list.view.StoresListView;
import com.demo.lcboapp.domain.util.Utils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.demo.lcboapp.Constants.DEFAULT_ITEMS_COUNT;
import static java.net.HttpURLConnection.HTTP_OK;

public class StoresListPresenter extends BaseMvpPresenter<StoresListView> {

	private Disposable mDisbosable;
	private LCBOApiService mApiService;
	private DatabaseHelper mDatabaseHelper;

	public StoresListPresenter() {
		mApiService = LCBOApp.getInstance().getApiService();
		mDatabaseHelper = DatabaseHelper.getInstance();
	}

	@Override
	public void detachView() {
		super.detachView();
		Utils.unsubscribe(mDisbosable);
	}

	public void loadStores(int page, String where, String query) {
		checkViewAttached();
		Utils.unsubscribe(mDisbosable);

		if (Utils.isNetworkConnected(LCBOApp.getInstance())) {
			mDisbosable = mApiService.getStoresList(page, DEFAULT_ITEMS_COUNT, where, query)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.doOnNext(response -> {
						if (response != null)
							mDatabaseHelper.saveStores(response.getResult());
					})
					.subscribeWith(createObserver());
		} else {
			mDisbosable = mDatabaseHelper.getStoresList(page, DEFAULT_ITEMS_COUNT, where, query)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribeWith(createObserver());
		}
	}

	private DisposableObserver<GetStoresListResponse> createObserver() {
		return new DisposableObserver<GetStoresListResponse>() {

			@Override
			public void onNext(@NonNull GetStoresListResponse response) {
				int status = response.getStatus();
				if (status == HTTP_OK) {
					List<Store> stores = response.getResult();
					if (!Utils.isNullOrEmpty(stores)) {
						getView().showStoresList(stores);
					} else {
						getView().showStoresEmpty();
					}
				} else {
					String error = response.getMessage();
					getView().showError(error);
				}
			}

			@Override
			public void onComplete() {}

			@Override
			public void onError(@NonNull Throwable e) {
				getView().showError(e.getMessage());
			}

		};
	}

}