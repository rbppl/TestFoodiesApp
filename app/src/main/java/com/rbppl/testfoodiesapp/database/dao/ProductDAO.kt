package com.rbppl.testfoodiesapp.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.rbppl.testfoodiesapp.database.dto.ProductWithCurtCountAndTags
import com.rbppl.testfoodiesapp.database.dto.ProductWithTags
import com.rbppl.testfoodiesapp.database.entity.CartEntity
import com.rbppl.testfoodiesapp.database.entity.ProductEntity

import kotlinx.coroutines.flow.Flow
import java.lang.StringBuilder
@Dao
interface ProductDAO {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(vararg entity: ProductEntity)
	@Query("SELECT * FROM product_table")
	suspend fun getProductsWithTags(): List<ProductWithTags>
	@Query("SELECT pt.*, ct.count AS cart_count FROM product_table AS pt " +
			"LEFT JOIN cart_table AS ct ON ct.product_id = pt.id ")
	suspend fun getProductsWithCurtCount(): List<ProductWithCurtCountAndTags>
	@RawQuery(observedEntities = [ProductEntity::class, CartEntity::class])
	fun get(query: SupportSQLiteQuery): Flow<List<ProductWithCurtCountAndTags>>
	fun getWithRaw(
		name: String? = null,
		categoryID: Int? = null,
		tags: List<Int> = emptyList()
	):Flow<List<ProductWithCurtCountAndTags>> {
		val builder = StringBuilder()
		val params = ArrayList<Any>()
		builder.append("SELECT DISTINCT pt.*, ct.count AS cart_count FROM product_table AS pt ")
		builder.append("LEFT JOIN cart_table AS ct ON ct.product_id = pt.id ")
		builder.append("LEFT JOIN product_and_tag_table AS patt ON patt.product_id = pt.id ")
		if (!name.isNullOrEmpty()){
			if (params.isEmpty()) builder.append("WHERE ")
			else builder.append("AND ")
			builder.append("pt.product_name LIKE '%' || ? || '%' ")
			params.add(name)
		}
		categoryID?.let {
			if (params.isEmpty()) builder.append("WHERE ")
			else builder.append("AND ")
			builder.append("pt.category_id = ?")
			params.add(categoryID)
		}
		if (tags.isNotEmpty()){
			if (params.isEmpty()) builder.append("WHERE ")
			else builder.append("AND ")
			builder.append("patt.tag_id IN (")
			repeat(tags.size){
				builder.append("?")
				if (it != tags.size -1) builder.append(",")
			}
			builder.append(") ")
			params.addAll(tags)
		}
		if (!name.isNullOrEmpty()){
			builder.append("ORDER BY LENGTH(pt.product_name)")
		}
		return get(SimpleSQLiteQuery(builder.toString(), params.toArray()))
	}
	@Query("SELECT product_table.*, (SELECT cart_table.count FROM cart_table WHERE product_id = product_table.id) AS cart_count  FROM product_table WHERE id = :id")
	fun getByID(id: Int): Flow<ProductWithCurtCountAndTags?>
}