package com.demo.lcboapp.ui.store.details.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.lcboapp.R;
import com.demo.lcboapp.data.model.Store;
import com.demo.lcboapp.domain.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreDateView extends LinearLayout {

	@BindView(R.id.sunFrom) TextView mSunFromView;
	@BindView(R.id.sunTo) TextView mSunToView;
	@BindView(R.id.monFrom) TextView mMonFromView;
	@BindView(R.id.monTo) TextView mMonToView;
	@BindView(R.id.tueFrom) TextView mTueFromView;
	@BindView(R.id.tueTo) TextView mTueToView;
	@BindView(R.id.wedFrom) TextView mWedFromView;
	@BindView(R.id.wedTo) TextView mWedToView;
	@BindView(R.id.thuFrom) TextView mThuFromView;
	@BindView(R.id.thuTo) TextView mThuToView;
	@BindView(R.id.friFrom) TextView mFriFromView;
	@BindView(R.id.friTo) TextView mFriToView;
	@BindView(R.id.satFrom) TextView mSatFromView;
	@BindView(R.id.satTo) TextView mSatToView;

	public StoreDateView(Context context) {
		super(context);
		init();
	}

	public StoreDateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public StoreDateView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_store_dates, null);
		ButterKnife.bind(this, view);
		addView(view);
	}

	public void updateDates(Store store) {
		if (store == null)
			return;
		mSunFromView.setText(Utils.to24Time(store.getSundayOpen()));
		mSunToView.setText(Utils.to24Time(store.getSundayClose()));
		mMonFromView.setText(Utils.to24Time(store.getMondayOpen()));
		mMonToView.setText(Utils.to24Time(store.getMondayClose()));
		mTueFromView.setText(Utils.to24Time(store.getTuesdayOpen()));
		mTueToView.setText(Utils.to24Time(store.getTuesdayClose()));
		mWedFromView.setText(Utils.to24Time(store.getWednesdayOpen()));
		mWedToView.setText(Utils.to24Time(store.getWednesdayClose()));
		mThuFromView.setText(Utils.to24Time(store.getThursdayOpen()));
		mThuToView.setText(Utils.to24Time(store.getThursdayClose()));
		mFriFromView.setText(Utils.to24Time(store.getFridayOpen()));
		mFriToView.setText(Utils.to24Time(store.getFridayClose()));
		mSatFromView.setText(Utils.to24Time(store.getSaturdayOpen()));
		mSatToView.setText(Utils.to24Time(store.getSaturdayClose()));
	}

}