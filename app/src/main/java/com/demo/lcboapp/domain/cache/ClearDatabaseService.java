package com.demo.lcboapp.domain.cache;

import android.app.IntentService;
import android.content.Intent;

public class ClearDatabaseService extends IntentService {

	public ClearDatabaseService() {
		super("clear_database_service");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		DatabaseHelper.getInstance().clear();
	}

}