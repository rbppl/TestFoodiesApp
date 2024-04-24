package com.rbppl.testfoodiesapp.main.domain.repository
import com.rbppl.testfoodiesapp.main.domain.model.Category
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
interface NetworkRepository {
	suspend fun getMenu(): List<FoodItem>
	suspend fun getCategories(): List<Category>
}