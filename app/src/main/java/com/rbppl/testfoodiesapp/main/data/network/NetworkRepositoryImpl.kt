package com.rbppl.testfoodiesapp.main.data.network
import com.rbppl.testfoodiesapp.main.data.network.map.CategoryMap.mapToModel
import com.rbppl.testfoodiesapp.main.data.network.map.ProductMap.mapToModel
import com.rbppl.testfoodiesapp.main.domain.model.Category
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.repository.NetworkRepository
import com.rbppl.testfoodiesapp.network.ApiFoodies
class NetworkRepositoryImpl(
	private val networkApi: ApiFoodies,
	private val currency: String
): NetworkRepository {
	override suspend fun getMenu(): List<FoodItem> {
		return networkApi.product.getProducts().mapToModel(currency).map {it.copy(image = networkApi.getImageUrl(it.image)) }
	}
	override suspend fun getCategories(): List<Category> {
		return networkApi.category.getCategories().mapToModel()
	}
}