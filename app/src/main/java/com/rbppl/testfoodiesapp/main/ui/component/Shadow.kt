package com.rbppl.testfoodiesapp.main.ui.component
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import java.util.UUID
object Shadow {
	@Composable
	fun Box(
		modifier: Modifier = Modifier,
		offsetX: Dp = 0.dp,
		offsetY: Dp = 10.dp,
		shape: Shape = RoundedCornerShape(0.dp),
		brush: Brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Gray, Color.Transparent)),
		scale: Float = 1f,
		content: @Composable BoxScope.() -> Unit
	) {
		val shadowID = UUID.randomUUID()
		val contentID = UUID.randomUUID()
		val set = ConstraintSet {
			val shadowRef = createRefFor(shadowID)
			val contentRef = createRefFor(contentID)
			constrain(contentRef) {
				top.linkTo(parent.top)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				bottom.linkTo(parent.bottom)
			}
			constrain(shadowRef) {
				top.linkTo(contentRef.top)
				start.linkTo(contentRef.start)
				end.linkTo(contentRef.end)
				bottom.linkTo(contentRef.bottom)
				height = Dimension.fillToConstraints
				width = Dimension.fillToConstraints
			}
		}
		ConstraintLayout(
			modifier = modifier,
			constraintSet = set
		) {
			androidx.compose.foundation.layout.Box(
				modifier = Modifier
					.layoutId(shadowID)
					.offset(x = offsetX, y = offsetY)
					.scale(scale)
					.background(brush, shape)
			)
			androidx.compose.foundation.layout.Box(
				modifier = Modifier.layoutId(contentID),
				content = content
			)
		}
	}
}