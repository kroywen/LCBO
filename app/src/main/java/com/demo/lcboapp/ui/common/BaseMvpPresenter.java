package com.demo.lcboapp.ui.common;

public class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {

	private V mView;

	@Override
	public void attachView(V view) {
		mView = view;
	}

	@Override
	public void detachView() {
		mView = null;
	}

	public boolean isViewAttached() {
		return mView != null;
	}

	public V getView() {
		return mView;
	}

	public void checkViewAttached() {
		if (!isViewAttached())
			throw new MvpViewNotAttachedException();
	}

}