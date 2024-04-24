package com.rbppl.testfoodiesapp.main.domain.usecase
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
class GetCartUseCase(
	private val database: DatabaseRepository
) {
	fun getCartItems(): Flow<List<FoodItem>> = database.getCartItems()
}