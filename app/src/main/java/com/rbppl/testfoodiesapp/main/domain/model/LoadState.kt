package com.rbppl.testfoodiesapp.main.domain.model
sealed interface LoadState {
	data object Loading: LoadState
	data object Loaded : LoadState
	data class Error(val message: String) : LoadState
}