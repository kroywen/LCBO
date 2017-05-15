package com.demo.lcboapp.ui.common;

public class MvpViewNotAttachedException extends RuntimeException {

	public MvpViewNotAttachedException() {
		super("Please call Presenter.attachView(MvpView) before" +
				" requesting data to the Presenter");
	}

}