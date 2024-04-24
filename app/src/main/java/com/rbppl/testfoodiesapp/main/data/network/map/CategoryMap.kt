package com.rbppl.testfoodiesapp.main.data.network.map
import com.rbppl.testfoodiesapp.main.domain.model.Category
import com.rbppl.testfoodiesapp.network.response.CategoriesResponse
object CategoryMap {
    fun CategoriesResponse.toModel() = Category(
        id= id,
        name = name
    )
    fun List<CategoriesResponse>.mapToModel() = this.map { it.toModel() }
}