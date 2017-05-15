package com.demo.lcboapp.ui.store.list.view;

import com.demo.lcboapp.data.model.Store;
import com.demo.lcboapp.ui.common.MvpView;

import java.util.List;

public interface StoresListView extends MvpView {

	void showStoresList(List<Store> stores);

	void showStoresEmpty();

	void showError(String error);

}