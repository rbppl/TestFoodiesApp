package com.rbppl.testfoodiesapp.main.util
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
typealias x = Dp
typealias y = Dp
fun LayoutCoordinates.pxToDp(density: Density): Pair<x, y> {
	return (size.width / density.density).dp to (size.height / density.density).dp
}