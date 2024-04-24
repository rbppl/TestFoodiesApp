package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rbppl.testfoodiesapp.R
object TopBar {
	@Composable
	fun Show(
		text: String,
		onBack: () -> Unit,
		modifier: Modifier = Modifier
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = modifier
				.fillMaxWidth()
				.height(64.dp)
		) {
			Buttons.IconButtonWithBadge(
				icon = painterResource(id = R.drawable.ic_back),
				iconTint = MaterialTheme.colorScheme.primary,
				onClick = { onBack()},
				modifier = Modifier
			)
			Texts.TextHead6(text = text)
		}
	}
}