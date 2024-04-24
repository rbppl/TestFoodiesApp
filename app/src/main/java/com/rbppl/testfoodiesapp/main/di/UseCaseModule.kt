package com.rbppl.testfoodiesapp.main.di
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
import com.rbppl.testfoodiesapp.main.domain.repository.NetworkRepository
import com.rbppl.testfoodiesapp.main.domain.usecase.AddToCartUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetCartUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetCategoryListUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetCurrentPriceUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetItemByID
import com.rbppl.testfoodiesapp.main.domain.usecase.GetMenuUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.UpdateMenuUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
	@Singleton
	@Provides
	fun provideAddToCartUseCase(
		database: DatabaseRepository
	): AddToCartUseCase = AddToCartUseCase(database)
	@Singleton
	@Provides
	fun provideGetCartUseCase(
		database: DatabaseRepository
	): GetCartUseCase = GetCartUseCase(database)
	@Singleton
	@Provides
	fun provideGetCategoryUseCase(
		database: DatabaseRepository
	): GetCategoryListUseCase = GetCategoryListUseCase(database)
	@Singleton
	@Provides
	fun provideGetMenuUseCase(
		database: DatabaseRepository
	): GetMenuUseCase = GetMenuUseCase(database)
	@Singleton
	@Provides
	fun provideUpdateMenuUseCase(
		database: DatabaseRepository,
		api: NetworkRepository
	): UpdateMenuUseCase = UpdateMenuUseCase(database, api)
	@Singleton
	@Provides
	fun provideGetCurrentPriceUseCase(
		database: DatabaseRepository
	): GetCurrentPriceUseCase = GetCurrentPriceUseCase(database)
	@Singleton
	@Provides
	fun provideGetItemById(
		database: DatabaseRepository
	): GetItemByID = GetItemByID(database)
}