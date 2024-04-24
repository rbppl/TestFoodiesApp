package com.rbppl.testfoodiesapp.main.di
import android.content.Context
import com.rbppl.testfoodiesapp.database.FoodiesDatabaseProvider
import com.rbppl.testfoodiesapp.main.data.database.DatabaseRepositoryImpl
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
import com.rbppl.testfoodiesapp.main.util.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
	@Singleton
	@Provides
	fun provideDatabaseRepository(
		@ApplicationContext context: Context
	): DatabaseRepository = DatabaseRepositoryImpl(
		database = FoodiesDatabaseProvider.get(context, Config.DATABASE_NAME),
		currency = Config.CURRENCY
	)
}