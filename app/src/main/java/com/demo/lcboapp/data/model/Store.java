package com.demo.lcboapp.data.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Store {

	@SerializedName("id") int id;
	@SerializedName("is_dead") boolean isDead;
	@SerializedName("name") String name;
	@SerializedName("city") String city;
	@SerializedName("address_line_1") String addressLine1;
	@SerializedName("address_line_2") String addressLine2;
	@SerializedName("postal_code") String postalCode;
	@SerializedName("telephone") String telephone;
	@SerializedName("fax") String fax;
	@SerializedName("latitude") double latitude;
	@SerializedName("longitude") double longitude;
	@SerializedName("products_count") int productsCount;
	@SerializedName("inventory_count") int inventoryCount;
	@SerializedName("has_wheelchair_accessability") boolean hasWheelchairAccessability;
	@SerializedName("has_bilingual_services") boolean hasBilingualServices;
	@SerializedName("has_product_consultant") boolean hasProductConsultant;
	@SerializedName("has_tasting_bar") boolean hasTastingBar;
	@SerializedName("has_beer_cold_room") boolean hasBeerColdRoom;
	@SerializedName("has_special_occasion_permits") boolean hasSpecialOccasionPermits;
	@SerializedName("has_vintages_corner") boolean hasVintagesCorner;
	@SerializedName("has_parking") boolean hasParking;
	@SerializedName("has_transit_access") boolean hasTransitAccess;
	@SerializedName("sunday_open") int sundayOpen;
	@SerializedName("sunday_close") int sundayClose;
	@SerializedName("monday_open") int mondayOpen;
	@SerializedName("monday_close") int mondayClose;
	@SerializedName("tuesday_open") int tuesdayOpen;
	@SerializedName("tuesday_close") int tuesdayClose;
	@SerializedName("wednesday_open") int wednesdayOpen;
	@SerializedName("wednesday_close") int wednesdayClose;
	@SerializedName("thursday_open") int thursdayOpen;
	@SerializedName("thursday_close") int thursdayClose;
	@SerializedName("friday_open") int fridayOpen;
	@SerializedName("friday_close") int fridayClose;
	@SerializedName("saturday_open") int saturdayOpen;
	@SerializedName("saturday_close") int saturdayClose;
	@SerializedName("updated_at") String updatedAt;

	public Store() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean dead) {
		isDead = dead;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean hasCity() {
		return !TextUtils.isEmpty(city);
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public boolean hasAddressLine1() {
		return !TextUtils.isEmpty(addressLine1);
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public boolean hasAddressLine2() {
		return !TextUtils.isEmpty(addressLine2);
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public boolean hasPostalCode() {
		return !TextUtils.isEmpty(postalCode);
	}

	public boolean hasLocation() {
		return hasCity() || hasAddressLine1() || hasAddressLine2();
	}

	public String getLocation() {
		return new StringBuilder()
				.append(hasAddressLine1() ? addressLine1 : "")
				.append(hasAddressLine2() ? ", " + addressLine2 : "")
				.append(hasCity() || hasPostalCode() ? '\n' : "")
				.append(hasCity() ? city : "")
				.append(hasPostalCode() ? " " + postalCode : "")
				.toString();
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean hasTelephone() {
		return !TextUtils.isEmpty(telephone);
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public boolean hasFax() {
		return !TextUtils.isEmpty(fax);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getProductsCount() {
		return productsCount;
	}

	public void setProductsCount(int productsCount) {
		this.productsCount = productsCount;
	}

	public int getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public boolean hasWheelchairAccessability() {
		return hasWheelchairAccessability;
	}

	public void setHasWheelchairAccessability(boolean hasWheelchairAccessability) {
		this.hasWheelchairAccessability = hasWheelchairAccessability;
	}

	public boolean hasBilingualServices() {
		return hasBilingualServices;
	}

	public void setHasBilingualServices(boolean hasBilingualServices) {
		this.hasBilingualServices = hasBilingualServices;
	}

	public boolean hasProductConsultant() {
		return hasProductConsultant;
	}

	public void setHasProductConsultant(boolean hasProductConsultant) {
		this.hasProductConsultant = hasProductConsultant;
	}

	public boolean hasTastingBar() {
		return hasTastingBar;
	}

	public void setHasTastingBar(boolean hasTastingBar) {
		this.hasTastingBar = hasTastingBar;
	}

	public boolean hasBeerColdRoom() {
		return hasBeerColdRoom;
	}

	public void setHasBeerColdRoom(boolean hasBeerColdRoom) {
		this.hasBeerColdRoom = hasBeerColdRoom;
	}

	public boolean hasSpecialOccasionPermits() {
		return hasSpecialOccasionPermits;
	}

	public void setHasSpecialOccasionPermits(boolean hasSpecialOccasionPermits) {
		this.hasSpecialOccasionPermits = hasSpecialOccasionPermits;
	}

	public boolean hasVintagesCorner() {
		return hasVintagesCorner;
	}

	public void setHasVintagesCorner(boolean hasVintagesCorner) {
		this.hasVintagesCorner = hasVintagesCorner;
	}

	public boolean hasParking() {
		return hasParking;
	}

	public void setHasParking(boolean hasParking) {
		this.hasParking = hasParking;
	}

	public boolean hasTransitAccess() {
		return hasTransitAccess;
	}

	public void setHasTransitAccess(boolean hasTransitAccess) {
		this.hasTransitAccess = hasTransitAccess;
	}

	public int getSundayOpen() {
		return sundayOpen;
	}

	public void setSundayOpen(int sundayOpen) {
		this.sundayOpen = sundayOpen;
	}

	public int getSundayClose() {
		return sundayClose;
	}

	public void setSundayClose(int sundayClose) {
		this.sundayClose = sundayClose;
	}

	public int getMondayOpen() {
		return mondayOpen;
	}

	public void setMondayOpen(int mondayOpen) {
		this.mondayOpen = mondayOpen;
	}

	public int getMondayClose() {
		return mondayClose;
	}

	public void setMondayClose(int mondayClose) {
		this.mondayClose = mondayClose;
	}

	public int getTuesdayOpen() {
		return tuesdayOpen;
	}

	public void setTuesdayOpen(int tuesdayOpen) {
		this.tuesdayOpen = tuesdayOpen;
	}

	public int getTuesdayClose() {
		return tuesdayClose;
	}

	public void setTuesdayClose(int tuesdayClose) {
		this.tuesdayClose = tuesdayClose;
	}

	public int getWednesdayOpen() {
		return wednesdayOpen;
	}

	public void setWednesdayOpen(int wednesdayOpen) {
		this.wednesdayOpen = wednesdayOpen;
	}

	public int getWednesdayClose() {
		return wednesdayClose;
	}

	public void setWednesdayClose(int wednesdayClose) {
		this.wednesdayClose = wednesdayClose;
	}

	public int getThursdayOpen() {
		return thursdayOpen;
	}

	public void setThursdayOpen(int thursdayOpen) {
		this.thursdayOpen = thursdayOpen;
	}

	public int getThursdayClose() {
		return thursdayClose;
	}

	public void setThursdayClose(int thursdayClose) {
		this.thursdayClose = thursdayClose;
	}

	public int getFridayOpen() {
		return fridayOpen;
	}

	public void setFridayOpen(int fridayOpen) {
		this.fridayOpen = fridayOpen;
	}

	public int getFridayClose() {
		return fridayClose;
	}

	public void setFridayClose(int fridayClose) {
		this.fridayClose = fridayClose;
	}

	public int getSaturdayOpen() {
		return saturdayOpen;
	}

	public void setSaturdayOpen(int saturdayOpen) {
		this.saturdayOpen = saturdayOpen;
	}

	public int getSaturdayClose() {
		return saturdayClose;
	}

	public void setSaturdayClose(int saturdayClose) {
		this.saturdayClose = saturdayClose;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}