package com.rbppl.testfoodiesapp.main.domain.usecase
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
class GetItemByID(
	private val databaseRepository: DatabaseRepository
) {
	fun getItemByID(id: Int): Flow<FoodItem?> {
		return databaseRepository.getItemByID(id)
	}
}