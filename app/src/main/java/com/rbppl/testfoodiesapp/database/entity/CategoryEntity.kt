package com.rbppl.testfoodiesapp.database.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "category_table")
data class CategoryEntity(
	@ColumnInfo("id")
	@PrimaryKey(autoGenerate = false)
	val id: Int,
	@ColumnInfo("category_name")
	val name: String
)