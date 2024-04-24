package com.rbppl.testfoodiesapp.database.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rbppl.testfoodiesapp.database.dto.CartWithProduct
import com.rbppl.testfoodiesapp.database.entity.CartEntity

import kotlinx.coroutines.flow.Flow
@Dao
interface CartDAO {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(vararg entity: CartEntity)
	@Delete
	suspend fun delete(entity: CartEntity)
	@Query("DELETE FROM cart_table ")
	suspend fun clear()
	@Query("SELECT * FROM cart_table")
	fun getListFlow(): Flow<CartEntity>
	@Query("SELECT * FROM cart_table")
	fun getListWithProductFlow(): Flow<List<CartWithProduct>>
	@Query("SELECT SUM(ct.count * pt.price_current) AS result  FROM cart_table AS ct " +
			"JOIN product_table AS pt ON ct.product_id = pt.id")
	fun getCurrentPriceFlow(): Flow<Float?>
}