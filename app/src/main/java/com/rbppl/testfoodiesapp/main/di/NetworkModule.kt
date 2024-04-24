package com.rbppl.testfoodiesapp.main.di
import com.rbppl.testfoodiesapp.main.data.network.NetworkRepositoryImpl
import com.rbppl.testfoodiesapp.main.domain.repository.NetworkRepository
import com.rbppl.testfoodiesapp.main.util.Config
import com.rbppl.testfoodiesapp.network.ApiFoodies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
	@Singleton
	@Provides
	fun provideNetworkRepository(): NetworkRepository = NetworkRepositoryImpl(
		networkApi = ApiFoodies(baseUrl = Config.BASE_URL),
		currency = Config.CURRENCY
	)
}