package com.rbppl.testfoodiesapp.main.util
import kotlinx.coroutines.flow.MutableStateFlow
fun MutableStateFlow<Boolean>.setOpposite() {
	value = !value
}