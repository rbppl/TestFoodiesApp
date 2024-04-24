package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR_NEGATIVE
import com.rbppl.testfoodiesapp.main.ui.theme.TEXT_COLOR_SECOND
object Texts {
	@Composable
	fun TextHead4(
		text: String,
		modifier: Modifier = Modifier,
		textColor: Color = TEXT_COLOR,
		textAlign: TextAlign = TextAlign.Start
	){
		Text(
			text = text,
			modifier = modifier,
			fontSize = 34.sp,
			color = textColor,
			fontWeight = FontWeight.Normal,
			textAlign = textAlign,
			lineHeight = 38.sp
		)
	}
	@Composable
	fun TextHead6(
		text: String,
		modifier: Modifier = Modifier,
		textColor: Color = TEXT_COLOR,
		textAlign: TextAlign = TextAlign.Start
	){
		Text(
			text = text,
			modifier = modifier,
			fontSize = 24.sp,
			color = textColor,
			fontWeight = FontWeight.SemiBold,
			textAlign = textAlign
		)
	}
	@Composable
	fun TextBody1Primary(
		text: String,
		modifier: Modifier = Modifier,
		textColor: Color = TEXT_COLOR,
		onTextLayout: ((TextLayoutResult) -> Unit)? = null,
		textAlign: TextAlign = TextAlign.Start,
	) {
		Text(
			text = text,
			modifier = modifier,
			fontSize = 18.sp,
			color = textColor,
			textAlign = textAlign,
			onTextLayout = onTextLayout
		)
	}
	@Composable
	fun TextBody1Second(
		text: String,
		modifier: Modifier = Modifier
	) {
		Text(
			text = text,
			modifier = modifier,
			fontSize = 18.sp,
			color = TEXT_COLOR_SECOND
		)
	}
	@Composable
	fun TextBody2Primary(
		text: String,
		modifier: Modifier = Modifier
	) {
		Text(
			text = text,
			modifier = modifier,
			fontSize = 14.sp,
			color = TEXT_COLOR
		)
	}
	@Composable
	fun TextBody2Second(
		text: String,
		modifier: Modifier = Modifier,
		textAlign: TextAlign = TextAlign.Start
	) {
		Text(
			text = text,
			modifier = modifier,
			fontSize = 14.sp,
			color = TEXT_COLOR_SECOND,
			fontWeight = FontWeight.Normal,
			textAlign = textAlign
		)
	}
	@Composable
	fun TextBody2SecondCrossed(
		text: String,
		modifier: Modifier = Modifier,
		textColor: Color = TEXT_COLOR_SECOND,
		onTextLayout: ((TextLayoutResult) -> Unit)? = null,
		textAlign: TextAlign = TextAlign.Start
	) {
		Text(
			text = text,
			modifier = modifier,
			fontSize = 14.sp,
			color = textColor,
			textDecoration = TextDecoration.LineThrough,
			textAlign = textAlign,
			onTextLayout = onTextLayout
		)
	}
	@Composable
	fun TextBody3Negative(
		text: String,
		modifier: Modifier = Modifier,
		textColor: Color = TEXT_COLOR_NEGATIVE
	){
		Text(
			text = text,
			modifier = modifier,
			fontSize = 12.sp,
			color = textColor
		)
	}
}