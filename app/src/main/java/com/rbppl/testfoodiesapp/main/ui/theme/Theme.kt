package com.rbppl.testfoodiesapp.main.ui.theme
import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.rbppl.testfoodiesapp.main.util.ColorHelper
private val LightColorScheme = lightColorScheme(
	primary = PUMPKIN,
	background = Color.White,
	surface = LIGHT_GRAY,
	secondary = PurpleGrey40,
	tertiary = Pink40,
	surfaceTint = Color.White
)
@Composable
fun FoodiesTestTheme(
	content: @Composable () -> Unit
) {
	StatusBarHelper(backgroundColor = MaterialTheme.colorScheme.background)
	MaterialTheme(
		colorScheme = LightColorScheme,
		typography = Typography,
		content = content
	)
}
@Composable
fun StatusBarHelper(
	backgroundColor: Color
){
	val view = LocalView.current
	if (!view.isInEditMode) {
		SideEffect {
			val window = (view.context as Activity).window
			window.statusBarColor = backgroundColor.toArgb()
			WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = ColorHelper.isLight(backgroundColor.toArgb())
		}
	}
}