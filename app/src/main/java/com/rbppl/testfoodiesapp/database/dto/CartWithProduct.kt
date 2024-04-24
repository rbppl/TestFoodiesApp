package com.rbppl.testfoodiesapp.database.dto
import androidx.room.Embedded
import androidx.room.Relation
import com.rbppl.testfoodiesapp.database.entity.CartEntity
import com.rbppl.testfoodiesapp.database.entity.ProductAndTagEntity
import com.rbppl.testfoodiesapp.database.entity.ProductEntity
data class CartWithProduct(
	@Embedded val cart: CartEntity,
	@Relation(
		parentColumn = "product_id",
		entityColumn = "id"
	)
	val product: ProductEntity,
	@Relation(
		parentColumn = "product_id",
		entityColumn = "product_id"
	)
	val tags: List<ProductAndTagEntity>
)