package com.rbppl.testfoodiesapp.main.data.database
import com.rbppl.testfoodiesapp.database.FoodiesDatabaseProvider
import com.rbppl.testfoodiesapp.main.data.database.map.CartMap.mapToModel
import com.rbppl.testfoodiesapp.main.data.database.map.CartMap.toCartEntity
import com.rbppl.testfoodiesapp.main.data.database.map.CategoriesMap.mapToModel
import com.rbppl.testfoodiesapp.main.data.database.map.CategoriesMap.toEntity
import com.rbppl.testfoodiesapp.main.data.database.map.FoodItemMap.mapToModel
import com.rbppl.testfoodiesapp.main.data.database.map.FoodItemMap.toEntity
import com.rbppl.testfoodiesapp.main.data.database.map.FoodItemMap.toModel
import com.rbppl.testfoodiesapp.main.data.database.map.TagMap.toTagEntity
import com.rbppl.testfoodiesapp.main.domain.model.Category
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.model.PriceTag
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class DatabaseRepositoryImpl(
	private val database: FoodiesDatabaseProvider,
	private val currency: String
) : DatabaseRepository {
	override suspend fun updateCategories(categories: List<Category>) {
		database.category.insert(*categories.map { it.toEntity() }.toTypedArray())
	}
	override suspend fun updateMenu(items: List<FoodItem>) {
		database.product.insert(*items.map { it.toEntity() }.toTypedArray())
	}
	override suspend fun clear() {
		database.category.clear()
	}
	override fun getMenu(name: String?, categoryID: Int?, tags: List<PriceTag>): Flow<List<FoodItem>> {
		return database.product.getWithRaw(name, categoryID, tags.map { it.id }).mapToModel(currency)
	}
	override fun getCategories(): Flow<List<Category>> {
		return database.category.getList().mapToModel()
	}
	override fun getCartItems(): Flow<List<FoodItem>> {
		return database.cart.getListWithProductFlow().mapToModel(currency)
	}
	override fun getCurrentPrice(): Flow<Float> {
		return database.cart.getCurrentPriceFlow().map { it ?: 0f }
	}
	override suspend fun addToCart(item: FoodItem) {
		database.cart.insert(item.toCartEntity())
	}
	override suspend fun removeFromCart(item: FoodItem) {
		database.cart.delete(item.toCartEntity())
	}
	override suspend fun addTags(pairs: List<Pair<Int, Int>>) {
		database.tag.insert(*pairs.map { it.toTagEntity() }.toTypedArray())
	}
	override fun getItemByID(id: Int): Flow<FoodItem?> {
		return database.product.getById(id).map { it?.toModel(currency) }
	}
}