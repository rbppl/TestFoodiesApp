package com.rbppl.testfoodiesapp.main.data.database.map
import com.rbppl.testfoodiesapp.database.entity.ProductAndTagEntity
object TagMap {
	fun Pair<Int, Int>.toTagEntity() = ProductAndTagEntity(
		productID = first,
		tagID = second
	)
}