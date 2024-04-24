package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rbppl.testfoodiesapp.R
object TopLine {
	@Composable
	fun Show(
		onFilter: () -> Unit,
		onSearch: () -> Unit,
		modifier: Modifier = Modifier,
		filterCount: Int = 0
	) {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.height(64.dp)
			,
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Buttons.IconButtonWithBadge(
				icon = painterResource(id = R.drawable.ic_tune),
				count = filterCount,
				onClick = { onFilter() }
			)
			Icon(
				painter = painterResource(id = R.drawable.ic_logo),
				contentDescription = stringResource(R.string.logo),
				tint = MaterialTheme.colorScheme.primary,
				modifier = Modifier
					.width(271.dp)
					.height(44.dp)
			)
			Buttons.IconButtonWithBadge(
				icon = painterResource(id = R.drawable.ic_search),
				count = 0,
				onClick = { onSearch() }
			)
		}
	}
}