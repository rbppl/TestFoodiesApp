package com.rbppl.testfoodiesapp.main.util
fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
	val r = indexOfFirst(predicate)
	return if (r == -1) null else r
}