package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rbppl.testfoodiesapp.main.domain.model.Category
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR_NEGATIVE
import com.rbppl.testfoodiesapp.main.util.indexOfFirstOrNull
object Categories {
	@Composable
	fun Show(
		modifier: Modifier = Modifier,
		selectedCategory: Category? = null,
		onSelect: ((Category) -> Unit)? = null,
		categories: List<Category> = emptyList(),
	) {
		val scrollState = rememberScrollState()
		var selection by remember { mutableIntStateOf(categories.indexOfFirstOrNull { it.id == selectedCategory?.id } ?:0) }
		Row(
			horizontalArrangement = Arrangement.SpaceAround,
			verticalAlignment = Alignment.CenterVertically,
			modifier = modifier
				.horizontalScroll(scrollState)
				.padding(horizontal = 16.dp)
		) {
			categories.forEachIndexed { i, item ->
				Item(isSelected = selection == i, category = item, onSelect = {
					selection = i
					if (onSelect != null) onSelect(categories[selection])
				})
			}
		}
	}
	@Composable
	private fun Item(
		isSelected: Boolean,
		category: Category,
		onSelect: () -> Unit,
		modifier: Modifier = Modifier
	) {
		Buttons.CategoryButton(
			text = category.name,
			onClick = { onSelect() },
			containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
			textColor = if (isSelected) TEXT_COLOR_NEGATIVE else TEXT_COLOR,
			modifier = modifier
		)
	}
}