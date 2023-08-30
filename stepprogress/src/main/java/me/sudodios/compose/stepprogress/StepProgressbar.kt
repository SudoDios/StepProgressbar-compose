package me.sudodios.compose.stepprogress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

@Composable
fun StepProgressBar(
    size : Dp = 80.dp,
    spaceSize : Dp = 3.dp,
    steps : Int = 5,
    progressColors : Pair<Color,Color> = Pair(Color.White, Color.Yellow),
    progressBackgroundColor : Color = Color.White.copy(0.2f),
    progressWidth : Dp = 4.dp,
    progressBackgroundWidth : Dp = 4.dp,
    progress : Pair<Int,Float> = Pair(2,80f)
) {

    val isRtl = isRtl()

    Canvas(
        modifier = Modifier.size(size)
    ) {
        val totalSpace = steps * spaceSize.toPx()
        val lineSizes = (360f - totalSpace) / steps
        for (i in 0 until steps) {
            val segments = (spaceSize.toPx() * i) + (lineSizes * i)
            var finalStartAngle = segments - (90f - spaceSize.toPx() / 2f)
            drawArc(
                color = progressBackgroundColor,
                startAngle = finalStartAngle,
                sweepAngle = lineSizes,
                useCenter = false,
                size = Size(size.toPx(),size.toPx()),
                style = Stroke(width = progressBackgroundWidth.toPx(), cap = StrokeCap.Round)
            )
            val counter = if (isRtl) (steps - i) - 1 else i
            if (counter < progress.first - 1) {
                drawArc(
                    brush = getGradient(listOf(progressColors.first,progressColors.second),145.0,this.size),
                    startAngle = finalStartAngle,
                    sweepAngle = lineSizes,
                    useCenter = false,
                    size = Size(size.toPx(),size.toPx()),
                    style = Stroke(width = progressWidth.toPx(), cap = StrokeCap.Round)
                )
            } else if (counter == progress.first - 1) {
                val percentage = lineSizes * (progress.second / 100f)
                finalStartAngle = if (isRtl) finalStartAngle + (lineSizes * ((100f - progress.second)) / 100f) else finalStartAngle
                drawArc(
                    brush = getGradient(listOf(progressColors.first,progressColors.second),145.0,this.size),
                    startAngle = finalStartAngle,
                    sweepAngle = percentage,
                    useCenter = false,
                    size = Size(size.toPx(),size.toPx()),
                    style = Stroke(width = progressWidth.toPx(), cap = StrokeCap.Round)
                )
            }
        }
    }

}

@Composable
fun isRtl (): Boolean = LocalLayoutDirection.current == LayoutDirection.Rtl

private fun getGradient (colors : List<Color>,degree : Double,size: Size) : Brush {
    val angleInRadians = Math.toRadians(degree)
    val halfWidth = size.width / 2
    val halfHeight = size.height / 2
    val sinAngle = sin(angleInRadians)
    val cosAngle = cos(angleInRadians)
    val x0 = (halfWidth * (1 + sinAngle)).toFloat()
    val y0 = (halfHeight * (1 - cosAngle)).toFloat()
    val x1 = (halfWidth * (1 - sinAngle)).toFloat()
    val y1 = (halfHeight * (1 + cosAngle)).toFloat()
    return Brush.linearGradient(colors, start = Offset(x0,y0), end = Offset(x1,y1))
}


@Composable
fun LineStepProgressBar (
    modifier: Modifier,
    spaceSize : Dp = 6.dp,
    steps : Int = 5,
    progressColors : Pair<Color,Color> = Pair(Color.White, Color.White),
    progressBackgroundColor : Color = Color.White.copy(0.2f),
    progressWidth : Dp = 3.dp,
    progressBackgroundWidth : Dp = 3.dp,
    progress : Pair<Int,Float> = Pair(5,80f)
) {

    val isRtl = isRtl()

    Canvas(modifier = modifier) {

        val lineSizes = size.width / steps
        val centerH = size.height / 2f

        for (i in 0 until steps) {
            val stepSize = lineSizes * i
            val startLine = stepSize + (spaceSize.toPx() / 2)
            val endLine = stepSize + (lineSizes - (spaceSize.toPx() / 2))
            val lineLocations = if (!isRtl) floatArrayOf(startLine,endLine) else floatArrayOf(endLine,startLine)
            drawLine(
                color = progressBackgroundColor,
                start = Offset(lineLocations[0],centerH),
                end = Offset(lineLocations[1],centerH),
                strokeWidth = progressBackgroundWidth.toPx(),
                cap = StrokeCap.Round
            )
            val counter = if (isRtl) (steps - i) - 1 else i
            if (counter < progress.first - 1) {
                drawLine(
                    brush = Brush.linearGradient(listOf(progressColors.first,progressColors.second), start = Offset(0f,0f), end = Offset(size.width,size.height)),
                    start = Offset(lineLocations[0],centerH),
                    end = Offset(lineLocations[1],centerH),
                    strokeWidth = progressWidth.toPx(),
                    cap = StrokeCap.Round
                )
            } else if (counter == progress.first - 1) {
                if (progress.second != 0f) {
                    val percentage = (lineLocations[1] - lineLocations[0]) * (progress.second / 100f)
                    drawLine(
                        brush = Brush.linearGradient(listOf(progressColors.first,progressColors.second), start = Offset(0f,0f), end = Offset(size.width,size.height)),
                        start = Offset(lineLocations[0],centerH),
                        end = Offset(lineLocations[0] + percentage,centerH),
                        strokeWidth = progressWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
        }
    }

}
