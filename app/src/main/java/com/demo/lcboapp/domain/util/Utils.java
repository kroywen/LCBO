package com.demo.lcboapp.domain.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Collection;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class Utils {

	public static String getSelectedItems(String[] array, Integer[] selected) {
		if (array == null || array.length == 0)
			return null;
		if (selected == null || selected.length == 0)
			return null;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < selected.length; i++) {
			int position = selected[i];
			if (position < 0 || position >= array.length)
				continue;
			builder.append(array[position]);
			if (i < selected.length - 1)
				builder.append(',');
		}
		return builder.toString();
	}

	public static boolean isNullOrEmpty(Collection<?> c) {
		return c == null || c.isEmpty();
	}

	public static boolean isNullOrEmpty(Map<?, ?> m) {
		return m == null || m.isEmpty();
	}

	public static String getFormattedPrice(int cents) {
		return String.format("$%.2f", cents / 100.0f);
	}

	public static void unsubscribe(Disposable disposable) {
		if (disposable != null && !disposable.isDisposed())
			disposable.dispose();
	}

	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
	}

	public static String to24Time(int time) {
		int hour = time / 60;
		int mins = time % 60;
		return String.format("%02d:%02d", hour, mins);
	}

}