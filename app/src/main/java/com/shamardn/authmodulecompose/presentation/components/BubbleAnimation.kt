package com.shamardn.authmodulecompose.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shamardn.authmodulecompose.ui.theme.gray
import com.shamardn.authmodulecompose.ui.theme.orange

@Composable
fun BubbleAnimation(
    bubbleColor1: Color,
    bubbleColor2: Color,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    Box(
       modifier = modifier
    ){
        val xValue by infiniteTransition.animateFloat(
            initialValue = 100f,
            targetValue = 600f,
            animationSpec = infiniteRepeatable(
                animation = tween(7000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        val yValue by infiniteTransition.animateFloat(
            initialValue = 250f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val xValue2 by infiniteTransition.animateFloat(
            initialValue = 600f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween(5000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        val yValue2 by infiniteTransition.animateFloat(
            initialValue = 10f,
            targetValue = 250f,
            animationSpec = infiniteRepeatable(
                animation = tween(5000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val xValue3 by infiniteTransition.animateFloat(
            initialValue = 600f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(6000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        val yValue3 by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 250f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ){
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        bubbleColor1,
                        bubbleColor2
                    ),
                    start = Offset(xValue - 90 , yValue),
                    end = Offset(xValue + 90, yValue)
                ),
                radius = 40f,
                center = Offset(xValue, yValue)
            )

            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        bubbleColor2,
                        bubbleColor1
                    ),
                    start = Offset(xValue2 - 90 , yValue2),
                    end = Offset(xValue2 + 90, yValue2)
                ),
                radius = 30f,
                center = Offset(xValue2, yValue2)
            )

            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        bubbleColor1,
                        bubbleColor2
                    ),
                    start = Offset(xValue3 - 90 , yValue3),
                    end = Offset(xValue3 + 90, yValue3)
                ),
                radius = 50f,
                center = Offset(xValue3, yValue3)
            )
        }
    }
}

@Preview
@Composable
private fun BubbleAnimationPreview() {
    BubbleAnimation(
        bubbleColor1 = orange,
        bubbleColor2 = gray,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}