package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rbppl.testfoodiesapp.R
object Counter {
	@Composable
	fun Show(
		initValue: Int,
		modifier: Modifier = Modifier,
		onCountChange: ((Int) -> Unit)? = null,
		step: Int = 1,
		maxValue: Int = Int.MAX_VALUE,
		minValue: Int = 0,
		buttonColor: Color = Color.White,
	) {
		var count = if (initValue in minValue..maxValue) initValue else minValue
		Row(
			modifier = modifier,
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.size(48.dp)
					.clip(RoundedCornerShape(8.dp))
					.clickable(enabled = count > minValue) {
						count -= step
						if (onCountChange != null) onCountChange(count)
					}
					.background(buttonColor)
					.padding(4.dp)
			) {
				Icon(
					modifier = Modifier
						.size(24.dp),
					painter = painterResource(id = R.drawable.ic_remove),
					contentDescription = "Remove",
					tint = MaterialTheme.colorScheme.primary
				)
			}
			Texts.TextBody1Primary(text = count.toString())
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.size(48.dp)
					.clip(RoundedCornerShape(8.dp))
					.clickable(enabled = count < maxValue) {
						count += step
						if (onCountChange != null) onCountChange(count)
					}
					.background(buttonColor)
					.padding(4.dp)
			) {
				Icon(
					modifier = Modifier
						.size(24.dp),
					painter = painterResource(id = R.drawable.ic_add),
					contentDescription = "Add",
					tint = MaterialTheme.colorScheme.primary
				)
			}
		}
	}
}