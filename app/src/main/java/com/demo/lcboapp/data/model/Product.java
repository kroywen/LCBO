package com.demo.lcboapp.data.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class Product implements Serializable {

	@SerializedName("id") int id;
	private int storeId; // FIXME this field should be added to api
	@SerializedName("is_dead") boolean isDead;
	@SerializedName("name") String name;
	@SerializedName("tags") String tags;
	@SerializedName("is_discontinued") boolean isDiscontinued;
	@SerializedName("price_in_cents") int priceInCents;
	@SerializedName("regular_price_in_cents") int regularPriceInCents;
	@SerializedName("limited_time_offer_savings_in_cents") int limitedTimeOfferSavingsInCents;
	@SerializedName("limited_time_offer_ends_on") String limitedTimeOfferEndsOn;
	@SerializedName("bonus_reward_miles") int bonusRewardMiles;
	@SerializedName("bonus_reward_miles_ends_on") String bonusRewardMilesEndsOn;
	@SerializedName("stock_type") String stockType;
	@SerializedName("primary_category") String primaryCategory;
	@SerializedName("secondary_category") String secondaryCategory;
	@SerializedName("origin") String origin;
	@SerializedName("package") String pacage;
	@SerializedName("package_unit_type") String packageUnitType;
	@SerializedName("package_unit_volume_in_milliliters") int packageUnitVolumeInMilliliters;
	@SerializedName("total_package_units") int totalPackageUnits;
	@SerializedName("volume_in_milliliters") int volumeInMilliliters;
	@SerializedName("alcohol_content") int alcoholContent;
	@SerializedName("price_per_liter_of_alcohol_in_cents") int pricePerLiterOfAlcoholInCents;
	@SerializedName("price_per_liter_in_cents") int pricePerLiterInCents;
	@SerializedName("inventory_count") int inventoryCount;
	@SerializedName("inventory_volume_in_milliliters") int inventoryVolumeInMilliliters;
	@SerializedName("inventory_price_in_cents") int inventoryPriceInCents;
	@SerializedName("sugar_content") String sugarContent;
	@SerializedName("producer_name") String producerName;
	@SerializedName("released_on") String releasedOn;
	@SerializedName("has_value_added_promotion") boolean hasValueAddedPromotion;
	@SerializedName("has_limited_time_offer") boolean hasLimitedTimeOffer;
	@SerializedName("has_bonus_reward_miles") boolean hasBonusRewardMiles;
	@SerializedName("is_seasonal") boolean isSeasonal;
	@SerializedName("is_vqa") boolean isVqa;
	@SerializedName("is_ocb") boolean isOcb;
	@SerializedName("is_kosher") boolean isKosher;
	@SerializedName("value_added_promotion_description") String valueAddedPromotionDescription;
	@SerializedName("description") String description;
	@SerializedName("serving_suggestion") String servingSuggestion;
	@SerializedName("tasting_note") String tastingNote;
	@SerializedName("updated_at") String updatedAt;
	@SerializedName("image_thumb_url") String imageThumbUrl;
	@SerializedName("image_url") String imageUrl;
	@SerializedName("varietal") String varietal;
	@SerializedName("style") String style;
	@SerializedName("tertiary_category") String tertiaryCategory;
	@SerializedName("sugar_in_grams_per_liter") int sugarInGramsPerLiter;
	@SerializedName("clearance_sale_savings_in_cents") int clearanceSaleSavingsInCents;
	@SerializedName("has_clearance_sale") boolean hasClearanceSale;

	public Product() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public boolean isDiscontinued() {
		return isDiscontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		isDiscontinued = discontinued;
	}

	public int getPriceInCents() {
		return priceInCents;
	}

	public void setPriceInCents(int priceInCents) {
		this.priceInCents = priceInCents;
	}

	public int getRegularPriceInCents() {
		return regularPriceInCents;
	}

	public void setRegularPriceInCents(int regularPriceInCents) {
		this.regularPriceInCents = regularPriceInCents;
	}

	public boolean hasNewPrice() {
		return priceInCents != regularPriceInCents;
	}

	public int getLimitedTimeOfferSavingsInCents() {
		return limitedTimeOfferSavingsInCents;
	}

	public void setLimitedTimeOfferSavingsInCents(int limitedTimeOfferSavingsInCents) {
		this.limitedTimeOfferSavingsInCents = limitedTimeOfferSavingsInCents;
	}

	public String getLimitedTimeOfferEndsOn() {
		return limitedTimeOfferEndsOn;
	}

	public void setLimitedTimeOfferEndsOn(String limitedTimeOfferEndsOn) {
		this.limitedTimeOfferEndsOn = limitedTimeOfferEndsOn;
	}

	public int getBonusRewardMiles() {
		return bonusRewardMiles;
	}

	public void setBonusRewardMiles(int bonusRewardMiles) {
		this.bonusRewardMiles = bonusRewardMiles;
	}

	public String getBonusRewardMilesEndsOn() {
		return bonusRewardMilesEndsOn;
	}

	public void setBonusRewardMilesEndsOn(String bonusRewardMilesEndsOn) {
		this.bonusRewardMilesEndsOn = bonusRewardMilesEndsOn;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getPrimaryCategory() {
		return primaryCategory;
	}

	public void setPrimaryCategory(String primaryCategory) {
		this.primaryCategory = primaryCategory;
	}

	public String getSecondaryCategory() {
		return secondaryCategory;
	}

	public void setSecondaryCategory(String secondaryCategory) {
		this.secondaryCategory = secondaryCategory;
	}

	public boolean hasSecondaryCategory() {
		return !TextUtils.isEmpty(secondaryCategory);
	}

	public String getCategory() {
		String category = primaryCategory;
		category += hasSecondaryCategory() ? " (" + secondaryCategory + ")" : "";
		return category;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public boolean hasOrigin() {
		return !TextUtils.isEmpty(origin);
	}

	public String getPacage() {
		return pacage;
	}

	public void setPacage(String pacage) {
		this.pacage = pacage;
	}

	public String getPackageUnitType() {
		return packageUnitType;
	}

	public void setPackageUnitType(String packageUnitType) {
		this.packageUnitType = packageUnitType;
	}

	public int getPackageUnitVolumeInMilliliters() {
		return packageUnitVolumeInMilliliters;
	}

	public void setPackageUnitVolumeInMilliliters(int packageUnitVolumeInMilliliters) {
		this.packageUnitVolumeInMilliliters = packageUnitVolumeInMilliliters;
	}

	public int getTotalPackageUnits() {
		return totalPackageUnits;
	}

	public void setTotalPackageUnits(int totalPackageUnits) {
		this.totalPackageUnits = totalPackageUnits;
	}

	public int getVolumeInMilliliters() {
		return volumeInMilliliters;
	}

	public void setVolumeInMilliliters(int volumeInMilliliters) {
		this.volumeInMilliliters = volumeInMilliliters;
	}

	public int getAlcoholContent() {
		return alcoholContent;
	}

	public void setAlcoholContent(int alcoholContent) {
		this.alcoholContent = alcoholContent;
	}

	public String getAlcoholContentFormatted() {
		return String.format("%.2f%%", alcoholContent / 100.0f);
	}

	public int getPricePerLiterOfAlcoholInCents() {
		return pricePerLiterOfAlcoholInCents;
	}

	public void setPricePerLiterOfAlcoholInCents(int pricePerLiterOfAlcoholInCents) {
		this.pricePerLiterOfAlcoholInCents = pricePerLiterOfAlcoholInCents;
	}

	public int getPricePerLiterInCents() {
		return pricePerLiterInCents;
	}

	public void setPricePerLiterInCents(int pricePerLiterInCents) {
		this.pricePerLiterInCents = pricePerLiterInCents;
	}

	public int getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public int getInventoryVolumeInMilliliters() {
		return inventoryVolumeInMilliliters;
	}

	public void setInventoryVolumeInMilliliters(int inventoryVolumeInMilliliters) {
		this.inventoryVolumeInMilliliters = inventoryVolumeInMilliliters;
	}

	public int getInventoryPriceInCents() {
		return inventoryPriceInCents;
	}

	public void setInventoryPriceInCents(int inventoryPriceInCents) {
		this.inventoryPriceInCents = inventoryPriceInCents;
	}

	public String getSugarContent() {
		return sugarContent;
	}

	public void setSugarContent(String sugarContent) {
		this.sugarContent = sugarContent;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getProducer() {
		String producer = producerName;
		producer += hasOrigin() ? " (" + origin + ")" : "";
		return producer;
	}

	public String getReleasedOn() {
		return releasedOn;
	}

	public void setReleasedOn(String releasedOn) {
		this.releasedOn = releasedOn;
	}

	public boolean hasValueAddedPromotion() {
		return hasValueAddedPromotion;
	}

	public void setHasValueAddedPromotion(boolean hasValueAddedPromotion) {
		this.hasValueAddedPromotion = hasValueAddedPromotion;
	}

	public boolean hasLimitedTimeOffer() {
		return hasLimitedTimeOffer;
	}

	public void setHasLimitedTimeOffer(boolean hasLimitedTimeOffer) {
		this.hasLimitedTimeOffer = hasLimitedTimeOffer;
	}

	public boolean hasBonusRewardMiles() {
		return hasBonusRewardMiles;
	}

	public void setHasBonusRewardMiles(boolean hasBonusRewardMiles) {
		this.hasBonusRewardMiles = hasBonusRewardMiles;
	}

	public boolean isSeasonal() {
		return isSeasonal;
	}

	public void setSeasonal(boolean seasonal) {
		isSeasonal = seasonal;
	}

	public boolean isVqa() {
		return isVqa;
	}

	public void setVqa(boolean vqa) {
		isVqa = vqa;
	}

	public boolean isOcb() {
		return isOcb;
	}

	public void setOcb(boolean ocb) {
		isOcb = ocb;
	}

	public boolean isKosher() {
		return isKosher;
	}

	public void setKosher(boolean kosher) {
		isKosher = kosher;
	}

	public String getValueAddedPromotionDescription() {
		return valueAddedPromotionDescription;
	}

	public void setValueAddedPromotionDescription(String valueAddedPromotionDescription) {
		this.valueAddedPromotionDescription = valueAddedPromotionDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServingSuggestion() {
		return servingSuggestion;
	}

	public void setServingSuggestion(String servingSuggestion) {
		this.servingSuggestion = servingSuggestion;
	}

	public String getTastingNote() {
		return tastingNote;
	}

	public void setTastingNote(String tastingNote) {
		this.tastingNote = tastingNote;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getImageThumbUrl() {
		return imageThumbUrl;
	}

	public void setImageThumbUrl(String imageThumbUrl) {
		this.imageThumbUrl = imageThumbUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVarietal() {
		return varietal;
	}

	public void setVarietal(String varietal) {
		this.varietal = varietal;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTertiaryCategory() {
		return tertiaryCategory;
	}

	public void setTertiaryCategory(String tertiaryCategory) {
		this.tertiaryCategory = tertiaryCategory;
	}

	public int getSugarInGramsPerLiter() {
		return sugarInGramsPerLiter;
	}

	public void setSugarInGramsPerLiter(int sugarInGramsPerLiter) {
		this.sugarInGramsPerLiter = sugarInGramsPerLiter;
	}

	public int getClearanceSaleSavingsInCents() {
		return clearanceSaleSavingsInCents;
	}

	public void setClearanceSaleSavingsInCents(int clearanceSaleSavingsInCents) {
		this.clearanceSaleSavingsInCents = clearanceSaleSavingsInCents;
	}

	public boolean hasClearanceSale() {
		return hasClearanceSale;
	}

	public void setHasClearanceSale(boolean hasClearanceSale) {
		this.hasClearanceSale = hasClearanceSale;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Product))
			return false;

		Product p = (Product) o;
		return new EqualsBuilder()
				.append(id, p.id)
				.append(name, p.name)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(id)
				.append(name)
				.toHashCode();
	}

}