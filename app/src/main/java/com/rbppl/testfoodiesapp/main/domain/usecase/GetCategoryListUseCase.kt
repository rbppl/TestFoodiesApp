package com.rbppl.testfoodiesapp.main.domain.usecase
import com.rbppl.testfoodiesapp.main.domain.model.Category
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
class GetCategoryListUseCase(
	private val database: DatabaseRepository
) {
	fun getCategoryList() : Flow<List<Category>> = database.getCategories()
}