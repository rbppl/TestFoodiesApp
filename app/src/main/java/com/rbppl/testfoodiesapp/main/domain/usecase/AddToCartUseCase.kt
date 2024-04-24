package com.rbppl.testfoodiesapp.main.domain.usecase
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
class AddToCartUseCase(
	private val database: DatabaseRepository
) {
	suspend fun addToCart(item: FoodItem) {
		if (item.count > 0)	database.addToCart(item)
		else database.removeFromCart(item)
	}
}