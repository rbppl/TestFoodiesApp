package com.rbppl.testfoodiesapp.main.domain.repository
import com.rbppl.testfoodiesapp.main.domain.model.Category
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.model.PriceTag
import kotlinx.coroutines.flow.Flow
interface DatabaseRepository {
	suspend fun updateCategories(categories: List<Category>)
	suspend fun updateMenu(items: List<FoodItem>)
	suspend fun clear()
	fun getMenu(
		name: String?,
		categoryID: Int?,
		tags: List<PriceTag>
	): Flow<List<FoodItem>>
	fun getCategories(): Flow<List<Category>>
	fun getCartItems(): Flow<List<FoodItem>>
	fun getCurrentPrice(): Flow<Float>
	suspend fun addToCart(item: FoodItem)
	suspend fun removeFromCart(item: FoodItem)
	suspend fun addTags(pairs: List<Pair<Int,Int>>)
	fun getItemByID(id: Int): Flow<FoodItem?>
}