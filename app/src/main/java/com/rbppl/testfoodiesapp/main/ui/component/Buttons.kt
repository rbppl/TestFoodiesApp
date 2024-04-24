package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR
object Buttons {
	@Composable
	fun BasicButton(
		text: String,
		onClick: () -> Unit,
		modifier: Modifier = Modifier,
		icon: Painter? = null,
		containerColor: Color = MaterialTheme.colorScheme.primary,
		textColor: Color = TEXT_COLOR,
		horizontalPadding: Dp = 16.dp,
		verticalPadding: Dp = 16.dp
	) {
		Box(
			modifier = modifier
				.clip(RoundedCornerShape(8.dp))
				.background(containerColor)
				.clickable { onClick() }
				.padding(horizontal = horizontalPadding, vertical = verticalPadding),
			contentAlignment = Alignment.Center
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				if (icon != null) {
					Icon(painter = icon, contentDescription = "Icon", tint = textColor)
					Spacer(modifier = Modifier.size(8.dp))
				}
				Texts.TextBody1Primary(text = text, textColor = textColor)
			}
		}
	}
	@Composable
	fun CategoryButton(
		text: String,
		onClick: () -> Unit,
		modifier: Modifier = Modifier,
		icon: Painter? = null,
		containerColor: Color = MaterialTheme.colorScheme.primary,
		textColor: Color = TEXT_COLOR,
		horizontalPadding: Dp = 16.dp,
		verticalPadding: Dp = 12.dp
	) {
		BasicButton(
			text = text,
			onClick = { onClick() },
			modifier = modifier,
			icon = icon,
			containerColor = containerColor,
			textColor = textColor,
			horizontalPadding = horizontalPadding,
			verticalPadding = verticalPadding
		)
	}
	@Composable
	fun AddToCart(
		price: String,
		onClick: () -> Unit,
		modifier: Modifier = Modifier,
		oldPrice: String? = null,
		containerColor: Color = Color.White
	) {
		Row(
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically,
			modifier = modifier
				.clip(RoundedCornerShape(8.dp))
				.heightIn(min = 48.dp)
				.background(containerColor)
				.clickable { onClick() }
				.padding(horizontal = 4.dp, vertical = 4.dp)
		) {
			Texts.TextBody1Primary(text = price)
			if (oldPrice != null) {
				Spacer(modifier = Modifier.size(4.dp))
				Texts.TextBody2SecondCrossed(text = oldPrice)
			}
		}
	}
	@Composable
	fun IconButtonWithBadge(
		icon: Painter,
		onClick: () -> Unit,
		modifier: Modifier = Modifier,
		count: Int = 0,
		iconTint: Color = LocalContentColor.current
	) {
		Box(
			contentAlignment = Alignment.Center,
			modifier = modifier
				.size(48.dp)
				.clip(RoundedCornerShape(3.dp))
				.clickable { onClick() }
		) {
			Box(
				modifier = Modifier.padding(8.dp)
			) {
				Icon(painter = icon, contentDescription = "Icon", modifier = Modifier.size(24.dp), tint = iconTint)
			}
			if (count > 0) {
				Box(
					contentAlignment = Alignment.Center,
					modifier = Modifier
						.padding(2.dp)
						.align(Alignment.TopEnd)
						.size(21.dp)
						.clip(RoundedCornerShape(21.dp))
						.background(MaterialTheme.colorScheme.primary)
				) {
					Texts.TextBody3Negative(text = count.toString())
				}
			}
		}
	}
}


