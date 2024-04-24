package com.rbppl.testfoodiesapp.database.povider
import com.rbppl.testfoodiesapp.database.dao.ProductAndTagDAO
import com.rbppl.testfoodiesapp.database.entity.ProductAndTagEntity
class TagProvider(
	private val dao: ProductAndTagDAO
) {
	suspend fun insert(vararg entity: ProductAndTagEntity) = dao.insert(*entity)
}