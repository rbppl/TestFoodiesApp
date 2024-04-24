package com.rbppl.testfoodiesapp.main.ui.screen.details
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.rbppl.testfoodiesapp.R
import com.rbppl.testfoodiesapp.main.ui.component.Buttons
import com.rbppl.testfoodiesapp.main.ui.component.Shadow
import com.rbppl.testfoodiesapp.main.ui.component.Texts
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR_NEGATIVE
import com.rbppl.testfoodiesapp.main.ui.theme.TRANSPARENT_30
import com.rbppl.testfoodiesapp.main.util.Navigator

import java.util.UUID
object InfoScreen {
	@OptIn(ExperimentalGlideComposeApi::class)
	@Composable
	fun Show(
		itemID: Int,
		navigator: Navigator,
		modifier: Modifier = Modifier,
		vm: InfoViewModel = hiltViewModel()
	) {
		LaunchedEffect(key1 = itemID) { vm.foodItemID.value = itemID }
		val itemFlow by vm.foodItem.collectAsStateWithLifecycle()
		val item = itemFlow ?: return
		val scrollState = rememberScrollState()
		val inCart by vm.inCart.collectAsStateWithLifecycle(false)
		val contentID = UUID.randomUUID()
		val buttonAddID = UUID.randomUUID()
		val buttonBackID = UUID.randomUUID()
		val set = ConstraintSet {
			val contentRef = createRefFor(contentID)
			val buttonAddRef = createRefFor(buttonAddID)
			val buttonBackRef = createRefFor(buttonBackID)
			constrain(contentRef){
				top.linkTo(parent.top)
				bottom.linkTo(buttonAddRef.top)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				height = Dimension.fillToConstraints
				width = Dimension.fillToConstraints
			}
			constrain(buttonAddRef){
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				bottom.linkTo(parent.bottom)
			}
			constrain(buttonBackRef){
				start.linkTo(parent.start)
				top.linkTo(parent.top)
			}
		}
		ConstraintLayout(
			constraintSet = set,
			modifier = modifier.fillMaxSize()
		) {
			Column(
				modifier = Modifier
					.layoutId(contentID)
					.verticalScroll(scrollState)
			) {
				GlideImage(
					model = item.image,
					failure = placeholder(R.drawable.menu_item),
					contentScale = ContentScale.Crop,
					contentDescription = item.description,
					modifier = Modifier
						.fillMaxWidth()
						.aspectRatio(1f)
				)
				Texts.TextHead4(text = item.name, modifier = Modifier.padding(vertical = 3.dp, horizontal = 16.dp))
				Texts.TextBody2Second(text = item.description, modifier = Modifier.padding(vertical = 3.dp, horizontal = 16.dp))
				HorizontalDivider()
				CardInfo(name = stringResource(R.string.weight), state = "${item.measure} ${item.measureUnit}")
				HorizontalDivider()
				CardInfo(
					name = stringResource(R.string.energy_value),
					state = "${item.energyPer100Grams.toString().replace(".", ",")} ${stringResource(R.string.kcal)}"
				)
				HorizontalDivider()
				CardInfo(
					name = stringResource(R.string.proteins),
					state = "${item.proteinsPer100Grams.toString().replace(".", ",")} ${stringResource(R.string.grams)}"
				)
				HorizontalDivider()
				CardInfo(
					name = stringResource(R.string.fats),
					state = "${item.fatsPer100Grams.toString().replace(".", ",")} ${stringResource(R.string.grams)}"
				)
				HorizontalDivider()
				CardInfo(
					name = stringResource(R.string.carbohydrates),
					state = "${item.carbohydratesPer100Grams.toString().replace(".", ",")} ${stringResource(R.string.grams)}"
				)
				HorizontalDivider()
			}
			var buttonHeight by remember { mutableIntStateOf(Int.MAX_VALUE) }
			val percentInCart by animateFloatAsState(
				targetValue = if (inCart) 1f else 0f,
				label = "",
				animationSpec = tween(durationMillis = 200)
			)
			Shadow.Box(
				offsetY = (-10).dp,
				modifier = Modifier
					.layoutId(buttonAddID)
					.onGloballyPositioned { buttonHeight = it.size.height }
					.offset(y = (buttonHeight * percentInCart).dp)
			) {
				Box(
					modifier = Modifier
						.background(MaterialTheme.colorScheme.background)
						.padding(horizontal = 16.dp, vertical = 12.dp)
				) {
					Buttons.BasicButton(
						text = "${stringResource(R.string.add_to_cart)} ${item.priceCurrent} ${item.currency}",
						onClick = { vm.addToCart() },
						textColor = TEXT_COLOR_NEGATIVE,
						modifier = Modifier
							.fillMaxWidth()
							.background(MaterialTheme.colorScheme.background)
					)
				}
			}
			Shadow.Box(
				shape = RoundedCornerShape(48.dp),
				brush = Brush.radialGradient(listOf(TRANSPARENT_30, Color.Transparent)),
				offsetY = 4.dp,
				modifier = Modifier.layoutId(buttonBackID).padding(16.dp)
			) {
				IconButton(
					onClick = { navigator.popBackStack() },
					colors = IconButtonDefaults.iconButtonColors().copy(containerColor = MaterialTheme.colorScheme.background)
				) {
					Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "Back")
				}
			}
		}
	}
	@Composable
	private fun CardInfo(
		name: String,
		state: String
	) {
		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp, vertical = 12.dp)
		) {
			Texts.TextBody2Second(text = name)
			Texts.TextBody2Primary(text = state)
		}
	}
}