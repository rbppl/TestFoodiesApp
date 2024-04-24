package com.rbppl.testfoodiesapp.network.service

import com.rbppl.testfoodiesapp.network.response.CategoriesResponse
import retrofit2.http.GET
interface CategoryService {

	@GET("Categories.json")
	suspend fun getCategories(): List<CategoriesResponse>

}