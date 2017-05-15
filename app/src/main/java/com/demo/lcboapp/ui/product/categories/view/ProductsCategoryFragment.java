package com.demo.lcboapp.ui.product.categories.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.lcboapp.R;
import com.demo.lcboapp.ui.product.list.view.ProductsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsCategoryFragment extends Fragment {

	@BindView(R.id.viewPager) ViewPager viewPager;
	@BindView(R.id.tabs) TabLayout tabs;

	public static ProductsCategoryFragment newInstance() {
		return new ProductsCategoryFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.products_category, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		setupViewPager();
	}

	private void setupViewPager() {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
		adapter.addFragment(ProductsListFragment.newInstance(getString(R.string.beer)), R.string.beer);
		adapter.addFragment(ProductsListFragment.newInstance(getString(R.string.wine)), R.string.wine);
		adapter.addFragment(ProductsListFragment.newInstance(getString(R.string.spirits)), R.string.spirits);

		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(adapter);
		tabs.setupWithViewPager(viewPager);
	}

	private class ViewPagerAdapter extends FragmentStatePagerAdapter {

		private List<Fragment> fragments = new ArrayList<>();
		private List<String> titles = new ArrayList<>();

		public ViewPagerAdapter(FragmentManager manager) {
			super(manager);
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		public void addFragment(Fragment fragment, int titleResId) {
			fragments.add(fragment);
			titles.add(getString(titleResId));
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titles.get(position);
		}

	}

}