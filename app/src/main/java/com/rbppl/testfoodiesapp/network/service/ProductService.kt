package com.rbppl.testfoodiesapp.network.service

import com.rbppl.testfoodiesapp.network.response.ProductResponse
import retrofit2.http.GET
interface ProductService {
	@GET("Products.json")
	suspend fun getProducts(): List<ProductResponse>
}