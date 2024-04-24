package com.rbppl.testfoodiesapp.database



import android.content.Context
import com.rbppl.testfoodiesapp.database.povider.CartProvider
import com.rbppl.testfoodiesapp.database.povider.CategoryProvider
import com.rbppl.testfoodiesapp.database.povider.ProductProvider
import com.rbppl.testfoodiesapp.database.povider.TagProvider


class FoodiesDatabaseProvider private constructor(
    database: FoodiesDatabase
) {

    companion object{

        private val MAP_INSTANCE = HashMap<DatabaseName, FoodiesDatabaseProvider>()

        fun get(context: Context, databaseName: String): FoodiesDatabaseProvider {
            if (MAP_INSTANCE[databaseName] == null) synchronized(this) {
                if (MAP_INSTANCE[databaseName] == null) MAP_INSTANCE[databaseName] =
                    FoodiesDatabaseProvider(FoodiesDatabase.get(context, databaseName))
            }
            return MAP_INSTANCE[databaseName] as FoodiesDatabaseProvider
        }

    }

    val cart = CartProvider(database.cartDAO())
    val category = CategoryProvider(database.categoryDAO())
    val product = ProductProvider(database.productDAO())
    val tag = TagProvider(database.productAndTagDAO())






}