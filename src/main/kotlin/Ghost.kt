import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun ghost() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(250.dp)
            .alpha(0.7f)
    ) {
        Canvas(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .padding(16.dp)
                .scale(pulseScale().value)
        ) {

            val trianglePath = Path().let {
                it.moveTo(this.size.width * .20f, this.size.height * .77f)
                it.lineTo(this.size.width * .20f, this.size.height * 0.95f)
                it.lineTo(this.size.width * .37f, this.size.height * 0.86f)
                it.close()
                it
            }
            drawOval(
                color = Color.White,
                size = Size(this.size.width, this.size.height * 0.95f)
            )
            drawPath(
                path = trianglePath,
                color = Color.White,
                style = Stroke(width = 15f, cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.DarkGray, this.size.width / 16,
                Offset(this.size.width * 0.2f, this.size.height / 2)
            )
            drawCircle(
                color = Color.DarkGray, this.size.width / 16,
                Offset(this.size.width * 0.8f, this.size.height / 2)
            )
        }
    }
}

@Composable
fun pulseScale() = rememberInfiniteTransition().animateFloat(
    initialValue = 1f,
    targetValue = 1.2f,
    animationSpec = infiniteRepeatable(
        animation = tween(1000),
        repeatMode = RepeatMode.Reverse
    )
)

