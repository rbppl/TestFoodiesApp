package com.rbppl.testfoodiesapp.network.response
import com.google.gson.annotations.SerializedName
data class ProductResponse(
	@SerializedName("carbohydrates_per_100_grams")
	val carbohydratesPer100Grams: Number,
	@SerializedName("category_id")
	val categoryId: Int,
	@SerializedName("description")
	val description: String,
	@SerializedName("energy_per_100_grams")
	val energyPer100Grams: Number,
	@SerializedName("fats_per_100_grams")
	val fatsPer100Grams: Number,
	@SerializedName("id")
	val id: Int,
	@SerializedName("image")
	val image: String,
	@SerializedName("measure")
	val measure: Number,
	@SerializedName("measure_unit")
	val measureUnit: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("price_current")
	val priceCurrent: Number,
	@SerializedName("price_old")
	val priceOld: Number?,
	@SerializedName("proteins_per_100_grams")
	val proteinsPer100Grams: Number,
	@SerializedName("tag_ids")
	val tagIds: List<Int>
)