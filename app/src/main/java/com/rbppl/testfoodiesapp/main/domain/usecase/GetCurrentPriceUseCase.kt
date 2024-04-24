package com.rbppl.testfoodiesapp.main.domain.usecase
import com.rbppl.testfoodiesapp.main.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
class GetCurrentPriceUseCase(
	private val databaseRepository: DatabaseRepository
) {
	fun getCurrentPrice() : Flow<Float> {
		return databaseRepository.getCurrentPrice()
	}
}