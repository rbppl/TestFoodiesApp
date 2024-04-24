package com.rbppl.testfoodiesapp.main.util
import androidx.navigation.NavController
class Navigator(
	private val navController: NavController
) {
	val itemIdArg = "item_id"
	val catalogRoute = "catalog"
	val infoRoute = "info/{$itemIdArg}"
	val cartRoute = "cart"
	fun catalog() {
		navController.navigate(catalogRoute)
	}
	fun info(itemID: Int){
		navController.navigate(infoRoute.replace("{$itemIdArg}", itemID.toString()))
	}
	fun cart(){
		navController.navigate(cartRoute)
	}
	fun popBackStack(){
		navController.popBackStack()
	}
}