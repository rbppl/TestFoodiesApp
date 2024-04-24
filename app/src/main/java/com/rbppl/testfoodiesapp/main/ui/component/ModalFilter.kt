package com.rbppl.testfoodiesapp.main.ui.component
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rbppl.testfoodiesapp.R
import com.rbppl.testfoodiesapp.main.domain.model.PriceTag
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR_NEGATIVE
object ModalFilter {
	@SuppressLint("MutableCollectionMutableState")
	@Composable
	fun Show(
		filterList: List<PriceTag>,
		modifier: Modifier = Modifier,
		onButtonClick: (List<PriceTag>) -> Unit,
		filterName: String = stringResource(R.string.select_dishes),
		buttonText: String = "label",
		containerColor: Color = Color.Transparent
	) {
		val tagsCollection = PriceTag.entries.filterNot { it == PriceTag.DEFAULT }
		val selectedList by remember { mutableStateOf(ArrayList<PriceTag>().apply { addAll(filterList) }) }
		Column(
			modifier = modifier
				.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
				.background(containerColor)
		) {
			Texts.TextHead6(
				text = filterName, modifier = Modifier
					.fillMaxWidth()
					.padding(top = 32.dp, start = 24.dp, end = 24.dp)
			)
			Spacer(modifier = Modifier.size(8.dp))
			tagsCollection.forEachIndexed { i, item ->
				var selected by remember { mutableStateOf(filterList.contains(item)) }
				ComponentFilter.Show(
					text = stringResource(id = item.description),
					isChecked = selected,
					onCheck = {
						selected = !selected
						if (selected) selectedList.add(item) else selectedList.remove(item)
					},
					modifier = Modifier.fillMaxWidth(),
					horizontalPadding = 24.dp
				)
				if (i != tagsCollection.size - 1) HorizontalDivider()
			}
			Spacer(modifier = Modifier.size(8.dp))
			Buttons.BasicButton(
				text = buttonText,
				onClick = { onButtonClick(selectedList) },
				modifier = Modifier
					.fillMaxWidth()
					.padding(bottom = 32.dp, start = 24.dp, end = 24.dp),
				textColor = TEXT_COLOR_NEGATIVE
			)
		}
	}
}