package com.rbppl.testfoodiesapp.database.povider
import com.rbppl.testfoodiesapp.database.dao.CategoryDAO
import com.rbppl.testfoodiesapp.database.entity.CategoryEntity
class CategoryProvider(
	private val dao: CategoryDAO
) {
	suspend fun insert(vararg categoryEntity: CategoryEntity) = dao.insert(*categoryEntity)
	fun getList() = dao.getList()
	suspend fun clear() = dao.clear()
}