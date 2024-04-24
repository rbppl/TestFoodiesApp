package com.rbppl.testfoodiesapp.main.ui.component
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.rbppl.testfoodiesapp.R
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
object ItemCard {
	@OptIn(ExperimentalGlideComposeApi::class)
	@Composable
	fun Show(
		foodItem: FoodItem,
		modifier: Modifier = Modifier,
		onCount: ((Int) -> Unit)? = null,
		@DrawableRes defImage: Int = R.drawable.menu_item,
		contentHeightIn: Dp? = null
	) {
		var count = foodItem.count
		Box(modifier = modifier) {
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.heightIn(contentHeightIn ?: Dp.Unspecified),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				GlideImage(
					modifier = Modifier
						.fillMaxWidth()
						.aspectRatio(1f),
					model = foodItem.image,
					contentDescription = foodItem.name,
					failure = placeholder(defImage),
					contentScale = ContentScale.Crop
				)
				Texts.TextBody1Primary(
					text = foodItem.name, modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 16.dp)
				)
				Texts.TextBody2Second(
					text = "${foodItem.measure} ${foodItem.measureUnit}", modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 16.dp)
				)
				Spacer(modifier = Modifier.weight(1f, true))
				if (count == 0) {
					Buttons.AddToCart(
						price = "${foodItem.priceCurrent.toInt()} ${foodItem.currency}",
						onClick = {
							count++
							if (onCount != null) onCount(count)
						},
						oldPrice = if (foodItem.priceOld != null) "${foodItem.priceOld.toInt()} ${foodItem.currency}" else null,
						modifier = Modifier
							.padding(12.dp)
							.fillMaxWidth()
					)
				} else {
					Counter.Show(
						initValue = count,
						onCountChange = {
							count = it
							if (onCount != null) onCount(count)
						},
						modifier = Modifier
							.padding(12.dp)
							.fillMaxWidth()
					)
				}
			}
			Column(
				modifier = Modifier.padding(top = 12.dp, start = 12.dp)
			) {
				foodItem.tags.forEach {
					Image(
						painter = painterResource(id = it.icon),
						contentDescription = stringResource(id = it.description),
						modifier = Modifier.padding(bottom = 4.dp)
					)
				}
			}
		}
	}
}