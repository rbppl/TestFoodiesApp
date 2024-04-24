package com.rbppl.testfoodiesapp.database.povider
import com.rbppl.testfoodiesapp.database.dao.ProductDAO
import com.rbppl.testfoodiesapp.database.entity.ProductEntity
class ProductProvider(
	private val dao: ProductDAO
) {
	suspend fun insert(vararg entity: ProductEntity) = dao.insert(*entity)
	fun getWithRaw(
		name: String? = null,
		categoryID: Int? = null,
		tags: List<Int> = emptyList()
	) = dao.getWithRaw(name, categoryID, tags)
	fun getById(id: Int) = dao.getByID(id)
}