package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.rbppl.testfoodiesapp.R
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR_SECOND

import java.util.UUID
object TopLineSearch {
	@Composable
	fun Show(
		searchText: String,
		label: String,
		onBack: () -> Unit,
		onTextChanged: (String) -> Unit,
		modifier: Modifier = Modifier
	) {
		var text by rememberSaveable { mutableStateOf(searchText) }
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = modifier
				.fillMaxWidth()
				.height(64.dp)
		) {
			Buttons.IconButtonWithBadge(
				icon = painterResource(id = R.drawable.ic_back),
				count = 0,
				onClick = { onBack() },
				iconTint = MaterialTheme.colorScheme.primary
			)
			Spacer(modifier = Modifier.size(16.dp))
			Box(
				contentAlignment = Alignment.CenterStart,
				modifier = Modifier.fillMaxWidth()
			) {

				val tfID = UUID.randomUUID()
				val btnClearID = UUID.randomUUID()
				val constraints = ConstraintSet {
					val tfRef = createRefFor(tfID)
					val btnClearRef = createRefFor(btnClearID)
					constrain(tfRef) {
						top.linkTo(parent.top)
						bottom.linkTo(parent.bottom)
						start.linkTo(parent.start)
						end.linkTo(if (text.isNotEmpty()) btnClearRef.start else parent.end)
						width = Dimension.fillToConstraints
					}
					constrain(btnClearRef) {
						top.linkTo(parent.top)
						bottom.linkTo(parent.bottom)
						end.linkTo(parent.end)
					}
				}
				ConstraintLayout(
					constraintSet = constraints,
					modifier = Modifier.fillMaxWidth()
				) {
					BasicTextField(
						value = text,
						onValueChange = {
							text = it
							onTextChanged(text)
						},
						singleLine = true,
						textStyle = TextStyle.Default.copy(
							fontSize = 24.sp
						),
						modifier = Modifier
							.fillMaxWidth()
							.layoutId(tfID)
					)
					if (text.isNotEmpty()) {
						Row(modifier = Modifier.layoutId(btnClearID)) {
							Buttons.IconButtonWithBadge(
								icon = painterResource(id = R.drawable.ic_cancel),
								onClick = {
									text = ""
									onTextChanged(text)
								}
							)
						}

					}
				}
				if (text.isEmpty()) {
					Text(text = label, fontSize = 24.sp, color = TEXT_COLOR_SECOND)
				}
			}
		}
	}
}