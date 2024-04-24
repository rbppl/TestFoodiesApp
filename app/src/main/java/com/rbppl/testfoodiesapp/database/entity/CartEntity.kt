package com.rbppl.testfoodiesapp.database.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(
	tableName = "cart_table",
	foreignKeys = [ForeignKey(entity = ProductEntity::class, parentColumns = ["id"], childColumns = ["product_id"], onDelete = ForeignKey.CASCADE)]
)
data class CartEntity (
	@PrimaryKey(autoGenerate = false)
	@ColumnInfo("product_id")
	val productID: Int,
	@ColumnInfo("count")
	val count: Int
)