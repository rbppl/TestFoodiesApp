package com.rbppl.testfoodiesapp.main.domain.usecase
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.model.PriceTag
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
class GetMenuUseCase(
	private val databaseRepository: DatabaseRepository
) {

	fun getMenu(
		name: String?,
		categoryID: Int?,
		tags: List<PriceTag>
	): Flow<List<FoodItem>> = databaseRepository.getMenu(name, categoryID, tags)
}