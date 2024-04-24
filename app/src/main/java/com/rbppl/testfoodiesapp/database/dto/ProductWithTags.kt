package com.rbppl.testfoodiesapp.database.dto
import androidx.room.Embedded
import androidx.room.Relation
import com.rbppl.testfoodiesapp.database.entity.ProductAndTagEntity
import com.rbppl.testfoodiesapp.database.entity.ProductEntity
data class ProductWithTags(
	@Embedded
	val product: ProductEntity,
	@Relation(
		parentColumn = "id",
		entityColumn = "product_id",
	)
	val tags: List<ProductAndTagEntity>
)