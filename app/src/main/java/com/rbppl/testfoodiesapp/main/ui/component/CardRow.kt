package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.rbppl.testfoodiesapp.R
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
object CardRow {
	@OptIn(ExperimentalGlideComposeApi::class)
	@Composable
	fun Show(
		foodItem: FoodItem,
		modifier: Modifier = Modifier,
		onCount: ((Int) -> Unit)? = null,
		cardColor: Color = Color.Transparent,
		buttonColor: Color = Color.Transparent
	) {
		var count = foodItem.count
		val price = foodItem.priceCurrent.toFloat() * count
		val oldPrice = (foodItem.priceOld?.toFloat() ?: 0f) * count
		Box(
			modifier = modifier
				.clip(RoundedCornerShape(3.dp))
				.background(cardColor)
				.padding(16.dp)
		) {
			Row {
				GlideImage(
					modifier = Modifier
						.fillMaxWidth(0.25f)
						.aspectRatio(1f)
						.padding(end = 8.dp),
					model = foodItem.image,
					contentDescription = foodItem.name,
					failure = placeholder(R.drawable.menu_item)
				)
				Column(
					modifier = Modifier
						.fillMaxWidth(1f)
						.padding(start = 8.dp)
				) {
					Texts.TextBody2Primary(text = foodItem.name)
					Texts.TextBody2Primary(text = "${foodItem.measure} ${foodItem.measureUnit}")
					Spacer(modifier = Modifier.size(8.dp))
					Row {
						Counter.Show(
							initValue = count,
							onCountChange = {
								count = it
								if (onCount != null) onCount(it)
							},
							modifier = Modifier.fillMaxWidth(0.65f),
							buttonColor = buttonColor
						)
						Column(
							modifier = Modifier.fillMaxWidth(1f),
							verticalArrangement = Arrangement.Center
						) {
							Texts.TextBody1Primary(
								text = "${price.toInt()} ${foodItem.currency}",
								modifier = Modifier.fillMaxWidth(),
								textAlign = TextAlign.End
							)
							if (foodItem.priceOld != null) Texts.TextBody2SecondCrossed(
								text = "${oldPrice.toInt()} ${foodItem.currency}",
								modifier = Modifier.fillMaxWidth(),
								textAlign = TextAlign.End
							)
						}
					}
				}
			}
		}
	}
}