package com.demo.lcboapp;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.demo.lcboapp.domain.cache.ClearDatabaseReceiver;
import com.demo.lcboapp.domain.network.LCBOApiService;

import static com.demo.lcboapp.domain.cache.ClearDatabaseReceiver.ACTION_CLEAR_DATABASE;
import static com.demo.lcboapp.Constants.CLEAR_DATABASE_INTERVAL;

public class LCBOApp extends Application {

	private static LCBOApp mInstance;
	private LCBOApiService mApiService;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		scheduleDatabaseClearService();
	}

	public static LCBOApp getInstance() {
		return mInstance;
	}

	public LCBOApiService getApiService() {
		if (mApiService == null)
			mApiService = LCBOApiService.Creator.newApiService();
		return mApiService;
	}

	private void scheduleDatabaseClearService() {
		Intent intent = new Intent(this, ClearDatabaseReceiver.class);
		intent.setAction(ACTION_CLEAR_DATABASE);
		boolean isAlarmUp = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_NO_CREATE) != null;
		if (!isAlarmUp) {
			PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
			AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			alarmManager.setInexactRepeating(
					AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + CLEAR_DATABASE_INTERVAL,
					CLEAR_DATABASE_INTERVAL,
					pendingIntent
			);
		}
	}

}