package com.rbppl.testfoodiesapp.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rbppl.testfoodiesapp.database.dao.CartDAO
import com.rbppl.testfoodiesapp.database.dao.CategoryDAO
import com.rbppl.testfoodiesapp.database.dao.ProductAndTagDAO
import com.rbppl.testfoodiesapp.database.dao.ProductDAO
import com.rbppl.testfoodiesapp.database.entity.CartEntity
import com.rbppl.testfoodiesapp.database.entity.CategoryEntity
import com.rbppl.testfoodiesapp.database.entity.ProductAndTagEntity
import com.rbppl.testfoodiesapp.database.entity.ProductEntity
typealias DatabaseName = String
@Database(
    entities = [
        CategoryEntity::class,
        ProductEntity::class,
        CartEntity::class,
        ProductAndTagEntity::class
    ],
    version = 1
)
abstract class FoodiesDatabase: RoomDatabase() {
    abstract fun categoryDAO(): CategoryDAO
    abstract fun productDAO(): ProductDAO
    abstract fun productAndTagDAO(): ProductAndTagDAO
    abstract fun cartDAO(): CartDAO
    companion object{
        private val MAP_INSTANCE = HashMap<DatabaseName, FoodiesDatabase>()
        fun get(context: Context, databaseName: String): FoodiesDatabase {
            if (MAP_INSTANCE[databaseName] == null) synchronized(this) {
                if (MAP_INSTANCE[databaseName] == null) MAP_INSTANCE[databaseName] = Room
                    .databaseBuilder(context, FoodiesDatabase::class.java, databaseName)
                    .build()
            }
            return MAP_INSTANCE[databaseName] as FoodiesDatabase
        }
    }
}