package com.demo.lcboapp.domain.cache;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ClearDatabaseReceiver extends BroadcastReceiver {

	public static final String ACTION_CLEAR_DATABASE = "com.demo.lcboapp.ACTION_CLEAR_DATABASE";

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent serviceIntent = new Intent(context, ClearDatabaseService.class);
		context.startService(serviceIntent);
	}

}