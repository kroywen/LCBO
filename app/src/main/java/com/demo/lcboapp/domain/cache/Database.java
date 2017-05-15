package com.demo.lcboapp.domain.cache;

import android.content.ContentValues;
import android.database.Cursor;

import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.data.model.Store;

public class Database {

	public Database() {}

	public abstract static class StoresTable {

		public static final String TABLE_NAME = "stores";

		public static final String COLUMN_ID = "id";
		public static final String COLUMN_IS_DEAD = "is_dead";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_CITY = "city";
		public static final String COLUMN_ADDRESS_LINE_1 = "address_line_1";
		public static final String COLUMN_ADDRESS_LINE_2 = "address_line_2";
		public static final String COLUMN_POSTAL_CODE = "postal_code";
		public static final String COLUMN_TELEPHONE = "telephone";
		public static final String COLUMN_FAX = "fax";
		public static final String COLUMN_LATITUDE = "latitude";
		public static final String COLUMN_LONGITUDE = "longitude";
		public static final String COLUMN_PRODUCTS_COUNT = "products_count";
		public static final String COLUMN_INVENTORY_COUNT = "inventory_count";
		public static final String COLUMN_HAS_WHEELCHAIR_ACCESSABILITY = "has_wheelchair_accessability";
		public static final String COLUMN_HAS_BILINGUAL_SERVICES = "has_bilingual_services";
		public static final String COLUMN_HAS_PRODUCT_CONSULTANT = "has_product_consultant";
		public static final String COLUMN_HAS_TASTING_BAR = "has_tasting_bar";
		public static final String COLUMN_HAS_BEER_COLD_ROOM = "has_beer_cold_room";
		public static final String COLUMN_HAS_SPECIAL_OCCASION_PERMITS = "has_special_occasion_permits";
		public static final String COLUMN_HAS_VINTAGES_CORNER = "has_vintages_corner";
		public static final String COLUMN_HAS_PARKING = "has_parking";
		public static final String COLUMN_HAS_TRANSIT_ACCESS = "has_transit_access";
		public static final String COLUMN_SUNDAY_OPEN = "sunday_open";
		public static final String COLUMN_SUNDAY_CLOSE = "sunday_close";
		public static final String COLUMN_MONDAY_OPEN = "monday_open";
		public static final String COLUMN_MONDAY_CLOSE = "monday_close";
		public static final String COLUMN_TUESDAY_OPEN = "tuesday_open";
		public static final String COLUMN_TUESDAY_CLOSE = "tuesday_close";
		public static final String COLUMN_WEDNESDAY_OPEN = "wednesday_open";
		public static final String COLUMN_WEDNESDAY_CLOSE = "wednesday_close";
		public static final String COLUMN_THURSDAY_OPEN = "thursday_open";
		public static final String COLUMN_THURSDAY_CLOSE = "thursday_close";
		public static final String COLUMN_FRIDAY_OPEN = "friday_open";
		public static final String COLUMN_FRIDAY_CLOSE = "friday_close";
		public static final String COLUMN_SATURDAY_OPEN = "saturday_open";
		public static final String COLUMN_SATURDAY_CLOSE = "saturday_close";
		public static final String COLUMN_UPDATED_AT = "updated_at";

		public static final String CREATE =
				"CREATE TABLE " + TABLE_NAME + " (" +
						COLUMN_ID + " INTEGER PRIMARY KEY, " +
						COLUMN_IS_DEAD + " INTEGER, " +
						COLUMN_NAME + " TEXT, " +
						COLUMN_CITY + " TEXT, " +
						COLUMN_ADDRESS_LINE_1 + " TEXT, " +
						COLUMN_ADDRESS_LINE_2 + " TEXT, " +
						COLUMN_POSTAL_CODE + " TEXT, " +
						COLUMN_TELEPHONE + " TEXT, " +
						COLUMN_FAX + " TEXT, " +
						COLUMN_LATITUDE + " REAL, " +
						COLUMN_LONGITUDE + " REAL, " +
						COLUMN_PRODUCTS_COUNT + " INTEGER, " +
						COLUMN_INVENTORY_COUNT + " INTEGER, " +
						COLUMN_HAS_WHEELCHAIR_ACCESSABILITY + " INTEGER, " +
						COLUMN_HAS_BILINGUAL_SERVICES + " INTEGER, " +
						COLUMN_HAS_PRODUCT_CONSULTANT + " INTEGER, " +
						COLUMN_HAS_TASTING_BAR + " INTEGER, " +
						COLUMN_HAS_BEER_COLD_ROOM + " INTEGER, " +
						COLUMN_HAS_SPECIAL_OCCASION_PERMITS + " INTEGER, " +
						COLUMN_HAS_VINTAGES_CORNER + " INTEGER, " +
						COLUMN_HAS_PARKING + " INTEGER, " +
						COLUMN_HAS_TRANSIT_ACCESS + " INTEGER, " +
						COLUMN_SUNDAY_OPEN + " INTEGER, " +
						COLUMN_SUNDAY_CLOSE + " INTEGER, " +
						COLUMN_MONDAY_OPEN + " INTEGER, " +
						COLUMN_MONDAY_CLOSE + " INTEGER, " +
						COLUMN_TUESDAY_OPEN + " INTEGER, " +
						COLUMN_TUESDAY_CLOSE + " INTEGER, " +
						COLUMN_WEDNESDAY_OPEN + " INTEGER, " +
						COLUMN_WEDNESDAY_CLOSE + " INTEGER, " +
						COLUMN_THURSDAY_OPEN + " INTEGER, " +
						COLUMN_THURSDAY_CLOSE + " INTEGER, " +
						COLUMN_FRIDAY_OPEN + " INTEGER, " +
						COLUMN_FRIDAY_CLOSE + " INTEGER, " +
						COLUMN_SATURDAY_OPEN + " INTEGER, " +
						COLUMN_SATURDAY_CLOSE + " INTEGER, " +
						COLUMN_UPDATED_AT + " TEXT);";

		public static ContentValues toContentValues(Store store) {
			ContentValues values = new ContentValues();
			values.put(COLUMN_ID, store.getId());
			values.put(COLUMN_IS_DEAD, store.isDead() ? 1 : 0);
			values.put(COLUMN_NAME, store.getName());
			values.put(COLUMN_CITY, store.getCity());
			values.put(COLUMN_ADDRESS_LINE_1, store.getAddressLine1());
			values.put(COLUMN_ADDRESS_LINE_2, store.getAddressLine2());
			values.put(COLUMN_POSTAL_CODE, store.getPostalCode());
			values.put(COLUMN_TELEPHONE, store.getTelephone());
			values.put(COLUMN_FAX, store.getFax());
			values.put(COLUMN_LATITUDE, store.getLatitude());
			values.put(COLUMN_LONGITUDE, store.getLongitude());
			values.put(COLUMN_PRODUCTS_COUNT, store.getProductsCount());
			values.put(COLUMN_INVENTORY_COUNT, store.getInventoryCount());
			values.put(COLUMN_HAS_WHEELCHAIR_ACCESSABILITY, store.hasWheelchairAccessability() ? 1 : 0);
			values.put(COLUMN_HAS_BILINGUAL_SERVICES, store.hasBilingualServices() ? 1 : 0);
			values.put(COLUMN_HAS_PRODUCT_CONSULTANT, store.hasProductConsultant() ? 1 : 0);
			values.put(COLUMN_HAS_TASTING_BAR, store.hasTastingBar() ? 1 : 0);
			values.put(COLUMN_HAS_BEER_COLD_ROOM, store.hasBeerColdRoom() ? 1 : 0);
			values.put(COLUMN_HAS_SPECIAL_OCCASION_PERMITS, store.hasSpecialOccasionPermits() ? 1 : 0);
			values.put(COLUMN_HAS_VINTAGES_CORNER, store.hasVintagesCorner() ? 1 : 0);
			values.put(COLUMN_HAS_PARKING, store.hasParking() ? 1 : 0);
			values.put(COLUMN_HAS_TRANSIT_ACCESS, store.hasTransitAccess() ? 1 : 0);
			values.put(COLUMN_SUNDAY_OPEN, store.getSundayOpen());
			values.put(COLUMN_SUNDAY_CLOSE, store.getSundayClose());
			values.put(COLUMN_MONDAY_OPEN, store.getMondayOpen());
			values.put(COLUMN_MONDAY_CLOSE, store.getMondayClose());
			values.put(COLUMN_TUESDAY_OPEN, store.getTuesdayOpen());
			values.put(COLUMN_TUESDAY_CLOSE, store.getTuesdayClose());
			values.put(COLUMN_WEDNESDAY_OPEN, store.getWednesdayOpen());
			values.put(COLUMN_WEDNESDAY_CLOSE, store.getWednesdayClose());
			values.put(COLUMN_THURSDAY_OPEN, store.getThursdayOpen());
			values.put(COLUMN_THURSDAY_CLOSE, store.getThursdayClose());
			values.put(COLUMN_FRIDAY_OPEN, store.getFridayOpen());
			values.put(COLUMN_FRIDAY_CLOSE, store.getFridayClose());
			values.put(COLUMN_SATURDAY_OPEN, store.getSaturdayOpen());
			values.put(COLUMN_SATURDAY_CLOSE, store.getSaturdayClose());
			values.put(COLUMN_UPDATED_AT, store.getUpdatedAt());
			return values;
		}

		public static Store fromCursor(Cursor c) {
			Store store = new Store();
			store.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
			store.setDead(c.getInt(c.getColumnIndex(COLUMN_IS_DEAD)) == 1);
			store.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
			store.setCity(c.getString(c.getColumnIndex(COLUMN_CITY)));
			store.setAddressLine1(c.getString(c.getColumnIndex(COLUMN_ADDRESS_LINE_1)));
			store.setAddressLine2(c.getString(c.getColumnIndex(COLUMN_ADDRESS_LINE_2)));
			store.setPostalCode(c.getString(c.getColumnIndex(COLUMN_POSTAL_CODE)));
			store.setTelephone(c.getString(c.getColumnIndex(COLUMN_TELEPHONE)));
			store.setFax(c.getString(c.getColumnIndex(COLUMN_FAX)));
			store.setLatitude(c.getDouble(c.getColumnIndex(COLUMN_LATITUDE)));
			store.setLongitude(c.getDouble(c.getColumnIndex(COLUMN_LONGITUDE)));
			store.setProductsCount(c.getInt(c.getColumnIndex(COLUMN_PRODUCTS_COUNT)));
			store.setInventoryCount(c.getInt(c.getColumnIndex(COLUMN_INVENTORY_COUNT)));
			store.setHasWheelchairAccessability(c.getInt(c.getColumnIndex(COLUMN_HAS_WHEELCHAIR_ACCESSABILITY)) == 1);
			store.setHasBilingualServices(c.getInt(c.getColumnIndex(COLUMN_HAS_BILINGUAL_SERVICES)) == 1);
			store.setHasProductConsultant(c.getInt(c.getColumnIndex(COLUMN_HAS_PRODUCT_CONSULTANT)) == 1);
			store.setHasTastingBar(c.getInt(c.getColumnIndex(COLUMN_HAS_TASTING_BAR)) == 1);
			store.setHasBeerColdRoom(c.getInt(c.getColumnIndex(COLUMN_HAS_BEER_COLD_ROOM)) == 1);
			store.setHasSpecialOccasionPermits(c.getInt(c.getColumnIndex(COLUMN_HAS_SPECIAL_OCCASION_PERMITS)) == 1);
			store.setHasVintagesCorner(c.getInt(c.getColumnIndex(COLUMN_HAS_VINTAGES_CORNER)) == 1);
			store.setHasParking(c.getInt(c.getColumnIndex(COLUMN_HAS_PARKING)) == 1);
			store.setHasTransitAccess(c.getInt(c.getColumnIndex(COLUMN_HAS_TRANSIT_ACCESS)) == 1);
			store.setSundayOpen(c.getInt(c.getColumnIndex(COLUMN_SUNDAY_OPEN)));
			store.setSundayClose(c.getInt(c.getColumnIndex(COLUMN_SUNDAY_CLOSE)));
			store.setMondayOpen(c.getInt(c.getColumnIndex(COLUMN_MONDAY_OPEN)));
			store.setMondayClose(c.getInt(c.getColumnIndex(COLUMN_MONDAY_CLOSE)));
			store.setTuesdayOpen(c.getInt(c.getColumnIndex(COLUMN_TUESDAY_OPEN)));
			store.setTuesdayClose(c.getInt(c.getColumnIndex(COLUMN_TUESDAY_CLOSE)));
			store.setWednesdayOpen(c.getInt(c.getColumnIndex(COLUMN_WEDNESDAY_OPEN)));
			store.setWednesdayClose(c.getInt(c.getColumnIndex(COLUMN_WEDNESDAY_CLOSE)));
			store.setThursdayOpen(c.getInt(c.getColumnIndex(COLUMN_THURSDAY_OPEN)));
			store.setThursdayClose(c.getInt(c.getColumnIndex(COLUMN_THURSDAY_CLOSE)));
			store.setFridayOpen(c.getInt(c.getColumnIndex(COLUMN_FRIDAY_OPEN)));
			store.setFridayClose(c.getInt(c.getColumnIndex(COLUMN_FRIDAY_CLOSE)));
			store.setSaturdayOpen(c.getInt(c.getColumnIndex(COLUMN_SATURDAY_OPEN)));
			store.setSaturdayClose(c.getInt(c.getColumnIndex(COLUMN_SATURDAY_CLOSE)));
			store.setUpdatedAt(c.getString(c.getColumnIndex(COLUMN_UPDATED_AT)));
			return store;
		}

	}

	public abstract static class ProductsTable {

		public static final String TABLE_NAME = "products";

		public static final String COLUMN_ID = "id";
		public static final String COLUMN_STORE_ID = "store_id";
		public static final String COLUMN_IS_DEAD = "is_dead";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_TAGS = "tags";
		public static final String COLUMN_IS_DISCONTINUED = "is_discontinued";
		public static final String COLUMN_PRICE_IN_CENTS = "price_in_cents";
		public static final String COLUMN_REGULAR_PRICE_IN_CENTS = "regular_price_in_cents";
		public static final String COLUMN_LIMITED_TIME_OFFER_SAVINGS_IN_CENTS = "limited_time_offer_savings_in_cents";
		public static final String COLUMN_LIMITED_TIME_OFFER_ENDS_ON = "limited_time_offer_ends_on";
		public static final String COLUMN_BONUS_REWARD_MILES = "bonus_reward_miles";
		public static final String COLUMN_BONUS_REWARD_MILES_ENDS_ON = "bonus_reward_miles_ends_on";
		public static final String COLUMN_STOCK_TYPE = "stock_type";
		public static final String COLUMN_PRIMARY_CATEGORY = "primary_category";
		public static final String COLUMN_SECONDARY_CATEGORY = "secondary_category";
		public static final String COLUMN_ORIGIN = "origin";
		public static final String COLUMN_PACKAGE = "package";
		public static final String COLUMN_PACKAGE_UNIT_TYPE = "package_unit_type";
		public static final String COLUMN_PACKAGE_UNIT_VOLUME_IN_MILLILITERS = "package_unit_volume_in_milliliters";
		public static final String COLUMN_TOTAL_PACKAGE_UNITS = "total_package_units";
		public static final String COLUMN_VOLUME_IN_MILLILITERS = "volume_in_milliliters";
		public static final String COLUMN_ALCOHOL_CONTENT = "alcohol_content";
		public static final String COLUMN_PRICE_PER_LITER_OF_ALCOHOL_IN_CENTS = "price_per_liter_of_alcohol_in_cents";
		public static final String COLUMN_PRICE_PER_LITER_IN_CENTS = "price_per_liter_in_cents";
		public static final String COLUMN_INVENTORY_COUNT = "inventory_count";
		public static final String COLUMN_INVENTORY_VOLUME_IN_MILLILITERS = "inventory_volume_in_milliliters";
		public static final String COLUMN_INVENTORY_PRICE_IN_CENTS = "inventory_price_in_cents";
		public static final String COLUMN_SUGAR_CONTENT = "sugar_content";
		public static final String COLUMN_PRODUCER_NAME = "producer_name";
		public static final String COLUMN_RELEASED_ON = "released_on";
		public static final String COLUMN_HAS_VALUE_ADDED_PROMOTION = "has_value_added_promotion";
		public static final String COLUMN_HAS_LIMITED_TIME_OFFER = "has_limited_time_offer";
		public static final String COLUMN_HAS_BONUS_REWARD_MILES = "has_bonus_reward_miles";
		public static final String COLUMN_IS_SEASONAL = "is_seasonal";
		public static final String COLUMN_IS_VQA = "is_vqa";
		public static final String COLUMN_IS_OCB = "is_ocb";
		public static final String COLUMN_IS_KOSHER = "is_kosher";
		public static final String COLUMN_VALUE_ADDED_PROMOTION_DESCRIPTION = "value_added_promotion_description";
		public static final String COLUMN_DESCRIPTION = "description";
		public static final String COLUMN_SERVING_SUGGESTION = "serving_suggestion";
		public static final String COLUMN_TASTING_NOTE = "tasting_note";
		public static final String COLUMN_UPDATED_AT = "updated_at";
		public static final String COLUMN_IMAGE_THUMB_URL = "image_thumb_url";
		public static final String COLUMN_IMAGE_URL = "image_url";
		public static final String COLUMN_VARIETAL = "varietal";
		public static final String COLUMN_STYLE = "style";
		public static final String COLUMN_TERTIARY_CATEGORY = "tertiary_category";
		public static final String COLUMN_SUGAR_IN_GRAMS_PER_LITER = "sugar_in_grams_per_liter";
		public static final String COLUMN_CLEARANCE_SALE_SAVINGS_IN_CENTS = "clearance_sale_savings_in_cents";
		public static final String COLUMN_HAS_CLEARANCE_SALE = "has_clearance_sale";

		public static final String CREATE =
				"CREATE TABLE " + TABLE_NAME + " (" +
						COLUMN_ID + " INTEGER PRIMARY KEY, " +
						COLUMN_STORE_ID + " INTEGER, " +
						COLUMN_IS_DEAD + " INTEGER, " +
						COLUMN_NAME + " TEXT, " +
						COLUMN_TAGS + " TEXT, " +
						COLUMN_IS_DISCONTINUED + " INTEGER, " +
						COLUMN_PRICE_IN_CENTS + " INTEGER, " +
						COLUMN_REGULAR_PRICE_IN_CENTS + " INTEGER, " +
						COLUMN_LIMITED_TIME_OFFER_SAVINGS_IN_CENTS + " INTEGER, " +
						COLUMN_LIMITED_TIME_OFFER_ENDS_ON + " TEXT, " +
						COLUMN_BONUS_REWARD_MILES + " TEXT, " +
						COLUMN_BONUS_REWARD_MILES_ENDS_ON + " TEXT, " +
						COLUMN_STOCK_TYPE + " TEXT, " +
						COLUMN_PRIMARY_CATEGORY + " TEXT, " +
						COLUMN_SECONDARY_CATEGORY + " TEXT, " +
						COLUMN_ORIGIN + " TEXT, " +
						COLUMN_PACKAGE + " TEXT, " +
						COLUMN_PACKAGE_UNIT_TYPE + " TEXT, " +
						COLUMN_PACKAGE_UNIT_VOLUME_IN_MILLILITERS + " INTEGER, " +
						COLUMN_TOTAL_PACKAGE_UNITS + " INTEGER, " +
						COLUMN_VOLUME_IN_MILLILITERS + " INTEGER, " +
						COLUMN_ALCOHOL_CONTENT + " INTEGER, " +
						COLUMN_PRICE_PER_LITER_OF_ALCOHOL_IN_CENTS + " INTEGER, " +
						COLUMN_PRICE_PER_LITER_IN_CENTS + " INTEGER, " +
						COLUMN_INVENTORY_COUNT + " INTEGER, " +
						COLUMN_INVENTORY_VOLUME_IN_MILLILITERS + " INTEGER, " +
						COLUMN_INVENTORY_PRICE_IN_CENTS + " INTEGER, " +
						COLUMN_SUGAR_CONTENT + " TEXT, " +
						COLUMN_PRODUCER_NAME + " TEXT, " +
						COLUMN_RELEASED_ON + " TEXT, " +
						COLUMN_HAS_VALUE_ADDED_PROMOTION + " INTEGER, " +
						COLUMN_HAS_LIMITED_TIME_OFFER + " INTEGER, " +
						COLUMN_HAS_BONUS_REWARD_MILES + " INTEGER, " +
						COLUMN_IS_SEASONAL + " INTEGER, " +
						COLUMN_IS_VQA + " INTEGER, " +
						COLUMN_IS_OCB + " INTEGER, " +
						COLUMN_IS_KOSHER + " INTEGER, " +
						COLUMN_VALUE_ADDED_PROMOTION_DESCRIPTION + " TEXT, " +
						COLUMN_DESCRIPTION + " TEXT, " +
						COLUMN_SERVING_SUGGESTION + " TEXT, " +
						COLUMN_TASTING_NOTE + " TEXT, " +
						COLUMN_UPDATED_AT + " TEXT, " +
						COLUMN_IMAGE_THUMB_URL + " TEXT, " +
						COLUMN_IMAGE_URL + " TEXT, " +
						COLUMN_VARIETAL + " TEXT, " +
						COLUMN_STYLE + " TEXT, " +
						COLUMN_TERTIARY_CATEGORY + " TEXT, " +
						COLUMN_SUGAR_IN_GRAMS_PER_LITER + " INTEGER, " +
						COLUMN_CLEARANCE_SALE_SAVINGS_IN_CENTS + " INTEGER, " +
						COLUMN_HAS_CLEARANCE_SALE + " INTEGER);";

		public static ContentValues toContentValues(Product product) {
			ContentValues values = new ContentValues();
			values.put(COLUMN_ID, product.getId());
			values.put(COLUMN_STORE_ID, product.getStoreId());
			values.put(COLUMN_IS_DEAD, product.isDead() ? 1 : 0);
			values.put(COLUMN_NAME, product.getName());
			values.put(COLUMN_TAGS, product.getTags());
			values.put(COLUMN_IS_DISCONTINUED, product.isDiscontinued() ? 1 : 0);
			values.put(COLUMN_PRICE_IN_CENTS, product.getPriceInCents());
			values.put(COLUMN_REGULAR_PRICE_IN_CENTS, product.getRegularPriceInCents());
			values.put(COLUMN_LIMITED_TIME_OFFER_SAVINGS_IN_CENTS, product.getLimitedTimeOfferSavingsInCents());
			values.put(COLUMN_LIMITED_TIME_OFFER_ENDS_ON, product.getLimitedTimeOfferEndsOn());
			values.put(COLUMN_BONUS_REWARD_MILES, product.getBonusRewardMiles());
			values.put(COLUMN_BONUS_REWARD_MILES_ENDS_ON, product.getBonusRewardMilesEndsOn());
			values.put(COLUMN_STOCK_TYPE, product.getStockType());
			values.put(COLUMN_PRIMARY_CATEGORY, product.getPrimaryCategory());
			values.put(COLUMN_SECONDARY_CATEGORY, product.getSecondaryCategory());
			values.put(COLUMN_ORIGIN, product.getOrigin());
			values.put(COLUMN_PACKAGE, product.getPacage());
			values.put(COLUMN_PACKAGE_UNIT_TYPE, product.getPackageUnitType());
			values.put(COLUMN_PACKAGE_UNIT_VOLUME_IN_MILLILITERS, product.getPackageUnitVolumeInMilliliters());
			values.put(COLUMN_TOTAL_PACKAGE_UNITS, product.getTotalPackageUnits());
			values.put(COLUMN_VOLUME_IN_MILLILITERS, product.getVolumeInMilliliters());
			values.put(COLUMN_ALCOHOL_CONTENT, product.getAlcoholContent());
			values.put(COLUMN_PRICE_PER_LITER_OF_ALCOHOL_IN_CENTS, product.getPricePerLiterOfAlcoholInCents());
			values.put(COLUMN_PRICE_PER_LITER_IN_CENTS, product.getPricePerLiterInCents());
			values.put(COLUMN_INVENTORY_COUNT, product.getInventoryCount());
			values.put(COLUMN_INVENTORY_VOLUME_IN_MILLILITERS, product.getInventoryVolumeInMilliliters());
			values.put(COLUMN_INVENTORY_PRICE_IN_CENTS, product.getInventoryPriceInCents());
			values.put(COLUMN_SUGAR_CONTENT, product.getSugarContent());
			values.put(COLUMN_PRODUCER_NAME, product.getProducerName());
			values.put(COLUMN_RELEASED_ON, product.getReleasedOn());
			values.put(COLUMN_HAS_VALUE_ADDED_PROMOTION, product.hasValueAddedPromotion() ? 1 : 0);
			values.put(COLUMN_HAS_LIMITED_TIME_OFFER, product.hasLimitedTimeOffer() ? 1 : 0);
			values.put(COLUMN_HAS_BONUS_REWARD_MILES, product.hasBonusRewardMiles() ? 1 : 0);
			values.put(COLUMN_IS_SEASONAL, product.isSeasonal() ? 1 : 0);
			values.put(COLUMN_IS_VQA, product.isVqa() ? 1 : 0);
			values.put(COLUMN_IS_OCB, product.isOcb() ? 1 : 0);
			values.put(COLUMN_IS_KOSHER, product.isKosher() ? 1 : 0);
			values.put(COLUMN_VALUE_ADDED_PROMOTION_DESCRIPTION, product.getValueAddedPromotionDescription());
			values.put(COLUMN_DESCRIPTION, product.getDescription());
			values.put(COLUMN_SERVING_SUGGESTION, product.getServingSuggestion());
			values.put(COLUMN_TASTING_NOTE, product.getTastingNote());
			values.put(COLUMN_UPDATED_AT, product.getUpdatedAt());
			values.put(COLUMN_IMAGE_THUMB_URL, product.getImageThumbUrl());
			values.put(COLUMN_IMAGE_URL, product.getImageUrl());
			values.put(COLUMN_VARIETAL, product.getVarietal());
			values.put(COLUMN_STYLE, product.getStyle());
			values.put(COLUMN_TERTIARY_CATEGORY, product.getTertiaryCategory());
			values.put(COLUMN_SUGAR_IN_GRAMS_PER_LITER, product.getSugarInGramsPerLiter());
			values.put(COLUMN_CLEARANCE_SALE_SAVINGS_IN_CENTS, product.getClearanceSaleSavingsInCents());
			values.put(COLUMN_HAS_CLEARANCE_SALE, product.hasClearanceSale() ? 1 : 0);
			return values;
		}

		public static Product fromCursor(Cursor c) {
			Product product = new Product();
			product.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
			product.setStoreId(c.getColumnIndex(COLUMN_STORE_ID));
			product.setDead(c.getInt(c.getColumnIndex(COLUMN_IS_DEAD)) == 1);
			product.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
			product.setTags(c.getString(c.getColumnIndex(COLUMN_TAGS)));
			product.setDiscontinued(c.getInt(c.getColumnIndex(COLUMN_IS_DISCONTINUED)) == 1);
			product.setPriceInCents(c.getInt(c.getColumnIndex(COLUMN_PRICE_IN_CENTS)));
			product.setRegularPriceInCents(c.getInt(c.getColumnIndex(COLUMN_REGULAR_PRICE_IN_CENTS)));
			product.setLimitedTimeOfferSavingsInCents(c.getInt(c.getColumnIndex(COLUMN_LIMITED_TIME_OFFER_SAVINGS_IN_CENTS)));
			product.setLimitedTimeOfferEndsOn(c.getString(c.getColumnIndex(COLUMN_LIMITED_TIME_OFFER_ENDS_ON)));
			product.setBonusRewardMiles(c.getInt(c.getColumnIndex(COLUMN_BONUS_REWARD_MILES)));
			product.setBonusRewardMilesEndsOn(c.getString(c.getColumnIndex(COLUMN_BONUS_REWARD_MILES_ENDS_ON)));
			product.setStockType(c.getString(c.getColumnIndex(COLUMN_STOCK_TYPE)));
			product.setPrimaryCategory(c.getString(c.getColumnIndex(COLUMN_PRIMARY_CATEGORY)));
			product.setSecondaryCategory(c.getString(c.getColumnIndex(COLUMN_SECONDARY_CATEGORY)));
			product.setOrigin(c.getString(c.getColumnIndex(COLUMN_ORIGIN)));
			product.setPacage(c.getString(c.getColumnIndex(COLUMN_PACKAGE)));
			product.setPackageUnitType(c.getString(c.getColumnIndex(COLUMN_PACKAGE_UNIT_TYPE)));
			product.setPackageUnitVolumeInMilliliters(c.getInt(c.getColumnIndex(COLUMN_PACKAGE_UNIT_VOLUME_IN_MILLILITERS)));
			product.setTotalPackageUnits(c.getInt(c.getColumnIndex(COLUMN_TOTAL_PACKAGE_UNITS)));
			product.setVolumeInMilliliters(c.getInt(c.getColumnIndex(COLUMN_VOLUME_IN_MILLILITERS)));
			product.setAlcoholContent(c.getInt(c.getColumnIndex(COLUMN_ALCOHOL_CONTENT)));
			product.setPricePerLiterOfAlcoholInCents(c.getInt(c.getColumnIndex(COLUMN_PRICE_PER_LITER_OF_ALCOHOL_IN_CENTS)));
			product.setPricePerLiterInCents(c.getInt(c.getColumnIndex(COLUMN_PRICE_PER_LITER_IN_CENTS)));
			product.setInventoryCount(c.getInt(c.getColumnIndex(COLUMN_INVENTORY_COUNT)));
			product.setInventoryVolumeInMilliliters(c.getInt(c.getColumnIndex(COLUMN_INVENTORY_VOLUME_IN_MILLILITERS)));
			product.setInventoryPriceInCents(c.getInt(c.getColumnIndex(COLUMN_INVENTORY_PRICE_IN_CENTS)));
			product.setSugarContent(c.getString(c.getColumnIndex(COLUMN_SUGAR_CONTENT)));
			product.setProducerName(c.getString(c.getColumnIndex(COLUMN_PRODUCER_NAME)));
			product.setReleasedOn(c.getString(c.getColumnIndex(COLUMN_RELEASED_ON)));
			product.setHasValueAddedPromotion(c.getInt(c.getColumnIndex(COLUMN_HAS_VALUE_ADDED_PROMOTION)) == 1);
			product.setHasLimitedTimeOffer(c.getInt(c.getColumnIndex(COLUMN_HAS_LIMITED_TIME_OFFER)) == 1);
			product.setHasBonusRewardMiles(c.getInt(c.getColumnIndex(COLUMN_HAS_BONUS_REWARD_MILES)) == 1);
			product.setSeasonal(c.getInt(c.getColumnIndex(COLUMN_IS_SEASONAL)) == 1);
			product.setVqa(c.getInt(c.getColumnIndex(COLUMN_IS_VQA)) == 1);
			product.setOcb(c.getInt(c.getColumnIndex(COLUMN_IS_OCB)) == 1);
			product.setKosher(c.getInt(c.getColumnIndex(COLUMN_IS_KOSHER)) == 1);
			product.setValueAddedPromotionDescription(c.getString(c.getColumnIndex(COLUMN_VALUE_ADDED_PROMOTION_DESCRIPTION)));
			product.setDescription(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
			product.setServingSuggestion(c.getString(c.getColumnIndex(COLUMN_SERVING_SUGGESTION)));
			product.setTastingNote(c.getString(c.getColumnIndex(COLUMN_TASTING_NOTE)));
			product.setUpdatedAt(c.getString(c.getColumnIndex(COLUMN_UPDATED_AT)));
			product.setImageThumbUrl(c.getString(c.getColumnIndex(COLUMN_IMAGE_THUMB_URL)));
			product.setImageUrl(c.getString(c.getColumnIndex(COLUMN_IMAGE_URL)));
			product.setVarietal(c.getString(c.getColumnIndex(COLUMN_VARIETAL)));
			product.setStyle(c.getString(c.getColumnIndex(COLUMN_STYLE)));
			product.setTertiaryCategory(c.getString(c.getColumnIndex(COLUMN_TERTIARY_CATEGORY)));
			product.setSugarInGramsPerLiter(c.getInt(c.getColumnIndex(COLUMN_SUGAR_IN_GRAMS_PER_LITER)));
			product.setClearanceSaleSavingsInCents(c.getInt(c.getColumnIndex(COLUMN_CLEARANCE_SALE_SAVINGS_IN_CENTS)));
			product.setHasClearanceSale(c.getInt(c.getColumnIndex(COLUMN_HAS_CLEARANCE_SALE)) == 1);
			return product;
		}

	}

}