package com.rbppl.testfoodiesapp.database.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(
	tableName = "product_table",
	foreignKeys = [ForeignKey(parentColumns = ["id"], childColumns = ["category_id"], entity = CategoryEntity::class, onDelete = ForeignKey.CASCADE)]
)
data class ProductEntity(
	@ColumnInfo("id")
	@PrimaryKey(autoGenerate = false)
	val id: Int,
	@ColumnInfo("category_id")
	val categoryID: Int,
	@ColumnInfo("product_name")
	val name: String,
	@ColumnInfo("description")
	val description: String,
	@ColumnInfo("image_name")
	val imageName: String,
	@ColumnInfo("price_current")
	val priceCurrent: Float,
	@ColumnInfo("price_old")
	val priceOld: Float?,
	@ColumnInfo("measure")
	val measure: Int,
	@ColumnInfo("measure_unit")
	val measureUnit: String,
	@ColumnInfo("energy_per_100_grams")
	val energyPer100Grams: Float,
	@ColumnInfo("proteins_per_100_grams")
	val proteinsPer100Grams: Float,
	@ColumnInfo("fats_per_100_grams")
	val fatsPer100Grams: Float,
	@ColumnInfo("carbohydrates_per_100_grams")
	val carbohydratesPer100Grams: Float
)
