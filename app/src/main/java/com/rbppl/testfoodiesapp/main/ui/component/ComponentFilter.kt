package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
object ComponentFilter {
	@Composable
	fun Show(
		text: String,
		isChecked: Boolean,
		onCheck: () -> Unit,
		modifier: Modifier = Modifier,
		contentPadding: Dp = 12.dp,
		horizontalPadding: Dp = contentPadding,
		verticalPadding: Dp = contentPadding,
		startPadding: Dp = horizontalPadding,
		topPadding: Dp = verticalPadding,
		endPadding: Dp = horizontalPadding,
		bottomPadding: Dp = verticalPadding
	) {
		Row(
			modifier = modifier
				.clip(RoundedCornerShape(3.dp))
				.clickable { onCheck() }
				.padding(start = startPadding, top = topPadding, end = endPadding, bottom = bottomPadding),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Texts.TextBody1Primary(text = text)
			Checkbox(checked = isChecked, onCheckedChange = null)
		}
	}
}