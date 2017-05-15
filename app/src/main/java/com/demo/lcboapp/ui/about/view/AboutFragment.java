package com.demo.lcboapp.ui.about.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.lcboapp.R;
import com.vansuita.materialabout.builder.AboutBuilder;

public class AboutFragment extends Fragment {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return AboutBuilder.with(getActivity())
				.setAppIcon(R.mipmap.ic_launcher)
				.setAppName(R.string.app_name)
				.setVersionNameAsAppSubTitle()
				.addFiveStarsAction()
				.addShareAction(R.string.app_name)
				.setWrapScrollView(true)
				.setDividerHeight(0)
				.build();
	}

}