package com.rbppl.testfoodiesapp.main.ui.screen.details
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbppl.testfoodiesapp.main.domain.usecase.AddToCartUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetItemByID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class InfoViewModel @Inject constructor(
	getItemByID: GetItemByID,
	private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {
	val foodItemID = MutableStateFlow(0)
	@OptIn(ExperimentalCoroutinesApi::class)
	private val _foodItem = foodItemID.flatMapLatest { getItemByID.getItemByID(it) }
	val foodItem = _foodItem.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
	val inCart = foodItem.map { (it?.count ?: 0) > 0 }
	fun addToCart() {
		viewModelScope.launch {
			val item = foodItem.value ?: return@launch
			addToCartUseCase.addToCart(item.copy(count = 1))
		}
	}
}