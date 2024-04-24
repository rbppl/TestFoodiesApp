package com.rbppl.testfoodiesapp.database.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
@Entity(
	"product_and_tag_table",
	primaryKeys = ["product_id", "tag_id"],
	foreignKeys = [ForeignKey(entity = ProductEntity::class, parentColumns = ["id"], childColumns = ["product_id"], onDelete = ForeignKey.CASCADE)]
)
data class ProductAndTagEntity(
	@ColumnInfo("product_id")
	val productID: Int,
	@ColumnInfo("tag_id")
	val tagID: Int
)