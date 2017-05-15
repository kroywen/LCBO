package com.demo.lcboapp.ui.store.location.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.lcboapp.R;
import com.demo.lcboapp.data.model.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.demo.lcboapp.Constants.EXTRA_STORE_ADDRESS;
import static com.demo.lcboapp.Constants.EXTRA_STORE_LATITUDE;
import static com.demo.lcboapp.Constants.EXTRA_STORE_LONGITUDE;
import static com.demo.lcboapp.Constants.EXTRA_STORE_NAME;

public class StoreLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

	private String mStoreName;
	private String mStoreAddress;
	private double mStoreLatitude;
	private double mStoreLongitude;

	public static void show(Context context, Store store) {
		Intent intent = new Intent(context, StoreLocationActivity.class);
		intent.putExtra(EXTRA_STORE_NAME, store.getName());
		intent.putExtra(EXTRA_STORE_ADDRESS, store.getLocation());
		intent.putExtra(EXTRA_STORE_LATITUDE, store.getLatitude());
		intent.putExtra(EXTRA_STORE_LONGITUDE, store.getLongitude());
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_location);
		getIntentData();

		SupportMapFragment f = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		f.getMapAsync(this);
	}

	private void getIntentData() {
		Intent intent = getIntent();
		mStoreName = intent.getStringExtra(EXTRA_STORE_NAME);
		mStoreAddress = intent.getStringExtra(EXTRA_STORE_ADDRESS);
		mStoreLatitude = intent.getDoubleExtra(EXTRA_STORE_LATITUDE, 0);
		mStoreLongitude = intent.getDoubleExtra(EXTRA_STORE_LONGITUDE, 0);
	}

	@Override
	public void onMapReady(GoogleMap map) {
		map.animateCamera(
				CameraUpdateFactory.newLatLngZoom(new LatLng(mStoreLatitude, mStoreLongitude), 15),
				new GoogleMap.CancelableCallback() {
					@Override
					public void onFinish() {
						Marker marker = map.addMarker(new MarkerOptions()
								.position(new LatLng(mStoreLatitude, mStoreLongitude))
								.title(mStoreName)
								.snippet(mStoreAddress)
						);
						marker.showInfoWindow();
					}
					@Override
					public void onCancel() {}
		});
	}

}