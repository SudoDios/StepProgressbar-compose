package me.sudodios.compose.stepprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import me.sudodios.compose.stepprogress.LineStepProgressBar
import me.sudodios.compose.stepprogress.StepProgressBar
import me.sudodios.compose.stepprogressbar.ui.theme.StepProgressbarcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            StepProgressbarcomposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen()
                }
            }
        }
    }
}

@Preview
@Composable
fun Screen() {

    var steps by remember { mutableIntStateOf(6) }
    var progress by remember { mutableFloatStateOf(60f) }
    var currentStep by remember { mutableIntStateOf(4) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(12.dp))
        StepProgressBar(
            size = 150.dp,
            progressWidth = 7.dp,
            steps = steps,
            progressBackgroundWidth = 7.dp,
            progressColors = Pair(Color(0xFFCD7672), Color(0xFFEEB462)),
            gradientDegree = 145.0,
            progress = Pair(currentStep,progress),
            progressBackgroundColor = Color.White.copy(0.2f)
        )
        LineStepProgressBar(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp)
                .fillMaxWidth()
                .height(60.dp),
            spaceSize = 12.dp,
            progressWidth = 7.dp,
            steps = steps,
            progressBackgroundWidth = 7.dp,
            progressColors = Pair(Color(0xFFCD7672), Color(0xFFEEB462)),
            progress = Pair(currentStep,progress),
            progressBackgroundColor = Color.White.copy(0.2f)
        )
    }

}