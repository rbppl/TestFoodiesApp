package com.rbppl.testfoodiesapp.main.domain.model
data class FoodItem(
	val id: Int,
	val categoryID: Long,
	val name: String,
	val description: String,
	val image: String,
	val priceCurrent: Number,
	val priceOld: Number?,
	val measure: Int,
	val measureUnit: String,
	val currency: String,
	val energyPer100Grams: Number,
	val proteinsPer100Grams: Number,
	val fatsPer100Grams: Number,
	val carbohydratesPer100Grams: Number,
	val tags: List<PriceTag>,
	var count: Int = 0
)