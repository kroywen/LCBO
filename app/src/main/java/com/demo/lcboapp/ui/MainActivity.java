package com.demo.lcboapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.demo.lcboapp.R;
import com.demo.lcboapp.data.event.AddToCartEvent;
import com.demo.lcboapp.ui.about.view.AboutFragment;
import com.demo.lcboapp.ui.product.categories.view.ProductsCategoryFragment;
import com.demo.lcboapp.ui.product.search.view.ProductsSearchFragment;
import com.demo.lcboapp.ui.shoppingcart.model.ShoppingCart;
import com.demo.lcboapp.ui.shoppingcart.view.ShoppingCartFragment;
import com.demo.lcboapp.ui.store.list.view.StoresListFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.demo.lcboapp.Constants.DRAWER_ID_ABOUT;
import static com.demo.lcboapp.Constants.DRAWER_ID_PRODUCTS_BY_CATEGORIES;
import static com.demo.lcboapp.Constants.DRAWER_ID_PRODUCTS_SEARCH_WITH_OPTIONS;
import static com.demo.lcboapp.Constants.DRAWER_ID_SHOPPING_CART;
import static com.demo.lcboapp.Constants.DRAWER_ID_STORES;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.toolbar) Toolbar mToolbar;

	private Drawer mDrawer;
	private int mCurrentPageId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		setSupportActionBar(mToolbar);
		initDrawer();
	}

	private void initDrawer() {
		AccountHeader accountHeader = new AccountHeaderBuilder()
				.withActivity(this)
				.withHeaderBackground(R.drawable.drawer_header_background)
				.addProfiles(
						new ProfileDrawerItem()
								.withName("Max Sukov")
								.withEmail("sukovMax@gmail.com")
								.withIcon(R.drawable.profile_picture)
				)
				.build();

		mDrawer = new DrawerBuilder()
				.withActivity(this)
				.withToolbar(mToolbar)
				.withHeader(R.layout.drawer_header)
				.withAccountHeader(accountHeader)
				.addDrawerItems(
						new PrimaryDrawerItem().withIdentifier(DRAWER_ID_STORES)
								.withName(R.string.stores)
								.withIcon(R.drawable.ic_store_black_48dp),
						new PrimaryDrawerItem().withIdentifier(DRAWER_ID_PRODUCTS_BY_CATEGORIES)
								.withName(R.string.products_by_categories)
								.withIcon(R.drawable.ic_shop_black_48dp),
						new PrimaryDrawerItem().withIdentifier(DRAWER_ID_PRODUCTS_SEARCH_WITH_OPTIONS)
								.withName(R.string.products_search_with_options)
								.withIcon(R.drawable.ic_search_black_48dp),
						new PrimaryDrawerItem().withIdentifier(DRAWER_ID_SHOPPING_CART)
								.withName(R.string.shopping_cart)
								.withIcon(R.drawable.ic_shopping_cart_black_48dp)
								.withBadge(String.valueOf(ShoppingCart.getInstance().size())),
						new PrimaryDrawerItem().withIdentifier(DRAWER_ID_ABOUT)
								.withName(R.string.about)
								.withIcon(R.drawable.ic_info_black_48dp)
				)
				.withOnDrawerItemClickListener((view, position, drawerItem) ->
					handleDrawerItemClick((int) drawerItem.getIdentifier())
				)
				.build();

		mDrawer.setSelection(DRAWER_ID_STORES, true);
	}

	@Override
	protected void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public void onBackPressed() {
		if (mDrawer.isDrawerOpen()) {
			mDrawer.closeDrawer();
		} else {
			super.onBackPressed();
		}
	}

	private boolean handleDrawerItemClick(int id) {
		if (id != mCurrentPageId) {
			mCurrentPageId = id;
			Fragment f = getFragmentById(id);
			if (f != null) {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.content, f)
						.commit();
			}
		}
		return false;
	}

	private Fragment getFragmentById(int id) {
		switch (id) {
			case DRAWER_ID_STORES:
				return StoresListFragment.newInstance();
			case DRAWER_ID_PRODUCTS_BY_CATEGORIES:
				return ProductsCategoryFragment.newInstance();
			case DRAWER_ID_PRODUCTS_SEARCH_WITH_OPTIONS:
				return ProductsSearchFragment.newInstance();
			case DRAWER_ID_SHOPPING_CART:
				return ShoppingCartFragment.newInstance();
			case DRAWER_ID_ABOUT:
				return new AboutFragment();
			default:
				return null;
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onAddToCartEvent(AddToCartEvent event) {
		int badge = ShoppingCart.getInstance().size();
		mDrawer.updateBadge(DRAWER_ID_SHOPPING_CART,
				new StringHolder(String.valueOf(badge)));
	}

}