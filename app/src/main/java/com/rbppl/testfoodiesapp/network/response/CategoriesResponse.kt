package com.rbppl.testfoodiesapp.network.response
import com.google.gson.annotations.SerializedName
data class CategoriesResponse(
	@SerializedName("id")
	val id: Int,
	@SerializedName("name")
	val name: String
)

