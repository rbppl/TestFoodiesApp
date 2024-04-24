package com.rbppl.testfoodiesapp.main.domain.model
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rbppl.testfoodiesapp.R
enum class PriceTag(@DrawableRes val icon: Int, @StringRes val description: Int, val id: Int) {
	DEFAULT(0, R.string.default_type, 0),
	DISCOUNT(R.drawable.ic_discount, R.string.newest, 1),
	SPICY(R.drawable.ic_spicy, R.string.spicy, 4),
	VEGETARIAN(R.drawable.ic_vegetarian, R.string.vegetarian, 2),
	HIT(R.drawable.ic_discount, R.string.hit, 3),
	EXPRESS(R.drawable.ic_discount, R.string.express, 5);
	companion object {
		fun getByID(id: Int): PriceTag =  entries.firstOrNull { it.id == id } ?: DEFAULT
	}
}