package com.rbppl.testfoodiesapp.main.ui.screen.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbppl.testfoodiesapp.main.domain.model.Category
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.model.PriceTag
import com.rbppl.testfoodiesapp.main.domain.usecase.AddToCartUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetCategoryListUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetCurrentPriceUseCase
import com.rbppl.testfoodiesapp.main.domain.usecase.GetMenuUseCase
import com.rbppl.testfoodiesapp.main.util.setOpposite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
	getCategoryListUseCase: GetCategoryListUseCase,
	getMenuUseCase: GetMenuUseCase,
	getCurrentPriceUseCase: GetCurrentPriceUseCase,
	private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {
	val isSearching = MutableStateFlow(false)
	val isFiltering = MutableStateFlow(false)
	val searchText = MutableStateFlow("")
	val currentPrice = getCurrentPriceUseCase.getCurrentPrice().stateIn(viewModelScope, SharingStarted.Eagerly, 0f)
	private val _categories = getCategoryListUseCase.getCategoryList()
	val categories = _categories.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
	private val _selectedCategory = MutableStateFlow<Category?>(null)
	val selectedCategory = _selectedCategory.asStateFlow()
	private val _filters = MutableStateFlow<List<PriceTag>>(emptyList())
	val filters = _filters.asStateFlow()
	val filterCount = searchText.combine(_filters) { f, s -> s.size + if (f.isNotEmpty()) 1 else 0 }
	private val _searchedFlow = searchText
		.combine(selectedCategory) { _, _ -> true }
		.combine(_filters) { _, _ -> true }
	@OptIn(ExperimentalCoroutinesApi::class)
	val searchedFlow = _searchedFlow.flatMapLatest {
		getMenuUseCase.getMenu(
			name = searchText.value.ifEmpty { null },
			categoryID = selectedCategory.value?.id,
			tags = filters.value
		)
	}
	init {
		viewModelScope.launch {
			categories.collect {
				_selectedCategory.value = it.firstOrNull()
			}
		}
	}
	fun selectCategory(category: Category) {
		_selectedCategory.value = categories.value.firstOrNull { it == category }
	}
	fun filtersChange(filters: List<PriceTag>) {
		_filters.value = filters.map { it }
		isFiltering.setOpposite()
	}
	fun changePrice(item: FoodItem, count: Int) {
		viewModelScope.launch { addToCartUseCase.addToCart(item.copy(count = count)) }
	}
}