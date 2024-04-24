package com.rbppl.testfoodiesapp.main.ui.screen.cart
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rbppl.testfoodiesapp.R
import com.rbppl.testfoodiesapp.main.ui.component.Buttons
import com.rbppl.testfoodiesapp.main.ui.component.CardRow
import com.rbppl.testfoodiesapp.main.ui.component.Shadow
import com.rbppl.testfoodiesapp.main.ui.component.Texts
import com.rbppl.testfoodiesapp.main.ui.component.TopBar
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR_NEGATIVE
import com.rbppl.testfoodiesapp.main.util.Navigator
import java.util.UUID
object CartScreen {
	@Composable
	fun Show(
		navigator: Navigator,
		modifier: Modifier = Modifier,
		vm: CartViewModel = hiltViewModel()
	) {
		val items by vm.itemList.collectAsStateWithLifecycle()
		val price by vm.currentPrice.collectAsStateWithLifecycle()
		val barID = UUID.randomUUID()
		val contentID = UUID.randomUUID()
		val orderButtonID = UUID.randomUUID()
		val constrains = ConstraintSet {
			val barRef = createRefFor(barID)
			val contentRef = createRefFor(contentID)
			val orderButtonRef = createRefFor(orderButtonID)
			constrain(barRef) {
				top.linkTo(parent.top)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
			}
			constrain(contentRef) {
				top.linkTo(barRef.bottom)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				bottom.linkTo(if (price > 0) orderButtonRef.top else parent.bottom)
				height = Dimension.fillToConstraints
				width = Dimension.fillToConstraints
			}
			constrain(orderButtonRef) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				bottom.linkTo(parent.bottom)
			}
		}
		ConstraintLayout(
			constraintSet = constrains,
			modifier = modifier.fillMaxSize()
		) {
			if (items.isNotEmpty()) LazyVerticalGrid(
				columns = GridCells.Adaptive(340.dp),
				modifier = Modifier.layoutId(contentID)
			) {
				items(items.size) {
					Column {
						CardRow.Show(
							foodItem = items[it],
							onCount = { count -> vm.changePrice(items[it], count) },
							buttonColor = MaterialTheme.colorScheme.surface
						)
						HorizontalDivider()
					}
				}
			}
			else Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.layoutId(contentID)
					.padding(16.dp)
			) {
				Texts.TextBody1Second(text = stringResource(R.string.empty_select_a_dish_from_the_catalogue))
			}
			Shadow.Box(
				modifier = Modifier.layoutId(barID)
			) {
				TopBar.Show(
					text = stringResource(R.string.cart),
					onBack = { navigator.popBackStack() },
					modifier = Modifier
						.fillMaxWidth()
						.background(MaterialTheme.colorScheme.background)
				)
			}
			var modalFilterHeight by remember { mutableIntStateOf(Int.MAX_VALUE) }
			val percentFilterAnimator by animateFloatAsState(
				targetValue = if (price > 0) 1f else 0f,
				label = "",
				animationSpec = tween(durationMillis = 200)
			)
			if (percentFilterAnimator > 0) Shadow.Box(
				offsetY = (-10).dp,
				modifier = Modifier
					.layoutId(orderButtonID)
					.onGloballyPositioned { modalFilterHeight = it.size.height }
					.offset(y = (modalFilterHeight * (1f - percentFilterAnimator)).dp)
			) {
				Buttons.BasicButton(
					text = "${stringResource(R.string.order_for)} ${price.toInt()} ${items.firstOrNull()?.currency ?: 0}",
					onClick = { },
					textColor = TEXT_COLOR_NEGATIVE,
					modifier = Modifier
						.background(MaterialTheme.colorScheme.background)
						.padding(vertical = 12.dp, horizontal = 16.dp)
						.fillMaxWidth()
				)
			}
		}
	}
}