package com.rbppl.testfoodiesapp.database.povider
import com.rbppl.testfoodiesapp.database.dao.CartDAO
import com.rbppl.testfoodiesapp.database.entity.CartEntity
class CartProvider(
	private val dao: CartDAO
) {
	suspend fun insert(vararg entity: CartEntity) = dao.insert(*entity)
	suspend fun clear() = dao.clear()
	fun getListWithProductFlow() = dao.getListWithProductFlow()
	fun getCurrentPriceFlow() = dao.getCurrentPriceFlow()
	suspend fun delete(entity: CartEntity) = dao.delete(entity)
}