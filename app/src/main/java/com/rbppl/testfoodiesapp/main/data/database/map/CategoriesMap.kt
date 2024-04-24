package com.rbppl.testfoodiesapp.main.data.database.map
import com.rbppl.testfoodiesapp.database.entity.CategoryEntity
import com.rbppl.testfoodiesapp.main.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
object CategoriesMap {
	fun Category.toEntity() = CategoryEntity(
		id = id,
		name = name
	)
	fun CategoryEntity.toModel() = Category(
		id = id,
		name = name
	)
	fun Flow<List<CategoryEntity>>.mapToModel() = this.map { it.map { item -> item.toModel() } }
}