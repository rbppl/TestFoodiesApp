package com.rbppl.testfoodiesapp.main.ui.screen.catalog
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rbppl.testfoodiesapp.R
import com.rbppl.testfoodiesapp.main.domain.model.Category
import com.rbppl.testfoodiesapp.main.domain.model.FoodItem
import com.rbppl.testfoodiesapp.main.domain.model.PriceTag
import com.rbppl.testfoodiesapp.main.ui.component.Buttons
import com.rbppl.testfoodiesapp.main.ui.component.Categories
import com.rbppl.testfoodiesapp.main.ui.component.ItemCard
import com.rbppl.testfoodiesapp.main.ui.component.ModalFilter
import com.rbppl.testfoodiesapp.main.ui.component.Shadow
import com.rbppl.testfoodiesapp.main.ui.component.Texts
import com.rbppl.testfoodiesapp.main.ui.component.TopLine
import com.rbppl.testfoodiesapp.main.ui.component.TopLineSearch
import com.rbppl.testfoodiesapp.main.ui.theme.StatusBarHelper
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR_NEGATIVE
import com.rbppl.testfoodiesapp.main.ui.theme.TRANSPARENT_30
import com.rbppl.testfoodiesapp.main.util.ColorHelper
import com.rbppl.testfoodiesapp.main.util.Navigator
import com.rbppl.testfoodiesapp.main.util.pxToDp
import com.rbppl.testfoodiesapp.main.util.setOpposite
import kotlinx.coroutines.delay
import java.util.UUID
object CatalogScreen {
	@Composable
	fun Show(
		navigator: Navigator,
		vm: CatalogViewModel = hiltViewModel()
	) {
		val topBarID = UUID.randomUUID()
		val categoryID = UUID.randomUUID()
		val contentID = UUID.randomUUID()
		val buttonCartID = UUID.randomUUID()
		val filterID = UUID.randomUUID()
		val categoryShadowID = UUID.randomUUID()
		val buttonCardShadowID = UUID.randomUUID()
		val topBarShadowID = UUID.randomUUID()
		val isSearching by vm.isSearching.collectAsStateWithLifecycle()
		val isFiltering by vm.isFiltering.collectAsStateWithLifecycle()
		val filterCount by vm.filterCount.collectAsStateWithLifecycle(initialValue = 0)
		val categories by vm.categories.collectAsStateWithLifecycle()
		val selectedCategory by vm.selectedCategory.collectAsStateWithLifecycle()
		val itemList by vm.searchedFlow.collectAsStateWithLifecycle(emptyList())
		val searchText by vm.searchText.collectAsStateWithLifecycle()
		val filters by vm.filters.collectAsStateWithLifecycle()
		val currentPrice by vm.currentPrice.collectAsStateWithLifecycle()
		val constraints = ConstraintSet {
			val topBarRef = createRefFor(topBarID)
			val categoryRef = createRefFor(categoryID)
			val contentRef = createRefFor(contentID)
			val buttonCardRef = createRefFor(buttonCartID)
			val filterRef = createRefFor(filterID)
			val categoryShadowRef = createRefFor(categoryShadowID)
			val buttonCardShadowRef = createRefFor(buttonCardShadowID)
			val topButtonShadowRef = createRefFor(topBarShadowID)
			constrain(topBarRef) {
				top.linkTo(parent.top)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
			}
			constrain(topButtonShadowRef) {
				top.linkTo(topBarRef.top)
				start.linkTo(topBarRef.start)
				end.linkTo(topBarRef.end)
				bottom.linkTo(topBarRef.bottom)
				height = Dimension.fillToConstraints
				width = Dimension.fillToConstraints
			}
			constrain(categoryRef) {
				top.linkTo(topBarRef.bottom)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
			}
			constrain(categoryShadowRef) {
				top.linkTo(categoryRef.top)
				bottom.linkTo(categoryRef.bottom)
				start.linkTo(categoryRef.start)
				end.linkTo(categoryRef.end)
				height = Dimension.fillToConstraints
				width = Dimension.fillToConstraints
			}
			constrain(contentRef) {
				top.linkTo(if (isSearching) topBarRef.bottom else categoryRef.bottom)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				bottom.linkTo(if (currentPrice <= 0) parent.bottom else buttonCardRef.top)
				height = Dimension.fillToConstraints
				width = Dimension.fillToConstraints
			}
			constrain(buttonCardRef) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				bottom.linkTo(parent.bottom)
			}
			constrain(buttonCardShadowRef) {
				start.linkTo(buttonCardRef.start)
				end.linkTo(buttonCardRef.end)
				top.linkTo(buttonCardRef.top)
				bottom.linkTo(buttonCardRef.bottom)
				height = Dimension.fillToConstraints
				width = Dimension.fillToConstraints
			}
			constrain(filterRef) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				bottom.linkTo(parent.bottom)
			}
		}
		ConstraintLayout(
			constraintSet = constraints,
			modifier = Modifier
				.fillMaxSize()
		) {
			Content(contentID = contentID, itemList = itemList, vm = vm, filterCount = filterCount, navigator = navigator)
			TopBar(
				isSearching = isSearching,
				topBarID = topBarID,
				searchText = searchText,
				vm = vm,
				filterCount = filterCount,
				categoryID = categoryID,
				categories = categories,
				selectedCategory = selectedCategory
			)
			BottomBar(
				currentPrice = currentPrice,
				buttonCartID = buttonCartID,
				itemList = itemList,
				navigator = navigator,
				isFiltering = isFiltering,
				vm = vm,
				filters = filters,
				filterID = filterID
			)
		}
	}
	@Composable
	private fun Content(
		contentID: UUID,
		itemList: List<FoodItem>,
		vm: CatalogViewModel,
		filterCount: Int,
		navigator: Navigator
	) {
		var minHeight by remember { mutableStateOf(200.dp) }
		val density = LocalDensity.current
		if (itemList.isNotEmpty()) LazyVerticalGrid(
			columns = GridCells.Adaptive(170.dp),
			modifier = Modifier.layoutId(contentID),
			contentPadding = PaddingValues(12.dp)
		) {
			items(itemList.size) {
				ItemCard.Show(
					foodItem = itemList[it],
					onCount = { count -> vm.changePrice(itemList[it], count) },
					defImage = R.drawable.menu_item,
					contentHeightIn = minHeight,
					modifier = Modifier
						.padding(4.dp)
						.onGloballyPositioned { lc ->
							val h = lc.pxToDp(density).second
							if (h > minHeight) minHeight = h
						}
						.clip(RoundedCornerShape(8.dp))
						.background(MaterialTheme.colorScheme.surface)
						.clickable { navigator.info(itemList[it].id) }

				)
			}
		}
		else {
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.layoutId(contentID)
					.padding(16.dp)
			) {
				if (filterCount > 0) {
					Texts.TextBody1Second(text = stringResource(R.string.there_are_no_such_dishes_try_changing_filters))
				} else {
					Texts.TextBody1Second(text = stringResource(R.string.found_nothing))
				}
			}
		}
	}
	@Composable
	private fun TopBar(
		isSearching: Boolean,
		topBarID: UUID,
		searchText: String,
		vm: CatalogViewModel,
		filterCount: Int,
		categoryID: UUID,
		categories: List<Category>,
		selectedCategory: Category?
	) {
		if (isSearching) {
			val focusRegister = remember { FocusRequester() }
			LaunchedEffect(key1 = true) {
				delay(100)
				focusRegister.requestFocus()
			}
			Shadow.Box(modifier = Modifier.layoutId(topBarID)) {
				TopLineSearch.Show(
					searchText = searchText,
					label = stringResource(R.string.find_a_dish),
					onBack = { vm.isSearching.setOpposite() },
					onTextChanged = { vm.searchText.value = it },
					modifier = Modifier
						.background(MaterialTheme.colorScheme.background)
						.focusRequester(focusRegister)
				)
			}
		} else {
			TopLine.Show(
				onFilter = { vm.isFiltering.setOpposite() },
				onSearch = { vm.isSearching.setOpposite() },
				filterCount = filterCount,
				modifier = Modifier
					.layoutId(topBarID)
					.background(MaterialTheme.colorScheme.background)
			)
		}
		if (!isSearching) {
			Shadow.Box(modifier = Modifier.layoutId(categoryID)) {
				Categories.Show(
					categories = categories,
					selectedCategory = selectedCategory,
					onSelect = { vm.selectCategory(it) },
					modifier = Modifier
						.fillMaxWidth()
						.background(MaterialTheme.colorScheme.background)
						.padding(bottom = 12.dp)
				)
			}
		}
	}
	@Composable
	fun BottomBar(
		currentPrice: Float,
		buttonCartID: UUID,
		itemList: List<FoodItem>,
		navigator: Navigator,
		isFiltering: Boolean,
		vm: CatalogViewModel,
		filters: List<PriceTag>,
		filterID: UUID
	) {
		var cupButtonHeight by remember { mutableIntStateOf(Int.MAX_VALUE) }
		val percentPriceAnimator by animateFloatAsState(
			targetValue = if (currentPrice > 0) 1f else 0f,
			label = "",
			animationSpec = tween(durationMillis = 200)
		)
		Shadow.Box(
			offsetY = (-10).dp,
			modifier = Modifier
				.layoutId(buttonCartID)
				.offset(y = (cupButtonHeight * (1f - percentPriceAnimator)).dp)
		) {
			Buttons.BasicButton(
				text = "$currentPrice ${itemList.firstOrNull()?.currency ?: ""}",
				onClick = { navigator.cart() },
				icon = painterResource(id = R.drawable.ic_shopping_cart),
				textColor = TEXT_COLOR_NEGATIVE,
				modifier = Modifier
					.onGloballyPositioned { cupButtonHeight = it.size.height }
					.fillMaxWidth()
					.background(MaterialTheme.colorScheme.background)
					.padding(horizontal = 16.dp, vertical = 8.dp)

			)
		}
		var modalFilterHeight by remember { mutableIntStateOf(Int.MAX_VALUE) }
		val percentFilterAnimator by animateFloatAsState(
			targetValue = if (isFiltering) 1f else 0f,
			label = "",
			animationSpec = tween(durationMillis = 200)
		)
		val interactionSource = remember { MutableInteractionSource() }
		if (percentFilterAnimator != 0f) Box(modifier = Modifier
			.fillMaxSize()
			.alpha(percentFilterAnimator)
			.background(TRANSPARENT_30)
			.clickable(indication = null, interactionSource = interactionSource) { vm.isFiltering.setOpposite() })
		val background = ColorHelper.gradient(Color.White.toArgb(), Color.Black.toArgb(), percentFilterAnimator * 0.3f)
		StatusBarHelper(
			backgroundColor = Color(
				ColorHelper.gradient(
					MaterialTheme.colorScheme.background.toArgb(),
					background,
					percentFilterAnimator
				)
			)
		)
		if (percentFilterAnimator != 0f) ModalFilter.Show(
			filterList = filters,
			onButtonClick = { vm.filtersChange(it) },
			buttonText = stringResource(R.string.ready),
			containerColor = MaterialTheme.colorScheme.background,
			modifier = Modifier
				.layoutId(filterID)
				.onGloballyPositioned { modalFilterHeight = it.size.height }
				.offset(y = (modalFilterHeight * (1f - percentFilterAnimator)).dp)
				.fillMaxWidth()
				.clickable(enabled = false) {}
		)
	}
}