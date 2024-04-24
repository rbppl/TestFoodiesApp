package com.rbppl.testfoodiesapp.main.ui.screen.cart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.usecase.AddToCartUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetCartUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetCurrentPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CartViewModel @Inject constructor(
	getCartUseCase: GetCartUseCase,
	getCurrentPriceUseCase: GetCurrentPriceUseCase,
	private val addToCartUseCase: AddToCartUseCase
): ViewModel() {
	private val _itemList = getCartUseCase.getCartItems()
	val itemList = _itemList.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
	private val _currentPrice = getCurrentPriceUseCase.getCurrentPrice()
	val currentPrice = _currentPrice.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0f)
	fun changePrice(item: FoodItem, count: Int) {
		viewModelScope.launch { addToCartUseCase.addToCart(item.copy(count = count)) }
	}
}