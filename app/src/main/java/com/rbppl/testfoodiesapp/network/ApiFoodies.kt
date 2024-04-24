package com.rbppl.testfoodiesapp.network
import com.google.gson.GsonBuilder
import com.rbppl.testfoodiesapp.network.service.CategoryService
import com.rbppl.testfoodiesapp.network.service.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class ApiFoodies(private val baseUrl:String = "https://anika1d.github.io/WorkTestServer/"
) {
    private val gson = GsonBuilder().setLenient().create()
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val category = retrofit.create(CategoryService::class.java)
    val product = retrofit.create(ProductService::class.java)
    fun getImageUrl(imageName: String): String = baseUrl + imageName
}