import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp

@Composable
fun Ghost(ghostOffset: @Composable (Offset) -> Unit) {
    var ghostX by remember { mutableStateOf(0f) }
    var ghostY by remember { mutableStateOf(0f) }

    Column(
        Modifier
            .fillMaxSize()
            .alpha(0.7f)
    ) {
        Canvas(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .padding(16.dp)
                .scale(PulseScale().value)
                .onGloballyPositioned {
                    ghostX = it.positionInRoot().x
                    ghostY = it.positionInRoot().y
                }

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
                style = Stroke(width = 10f, cap = StrokeCap.Round)
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
    ghostOffset(Offset(ghostX, ghostY))

}

@Composable
fun PulseScale() = rememberInfiniteTransition().animateFloat(
    initialValue = 1f,
    targetValue = 1.2f,
    animationSpec = infiniteRepeatable(
        animation = tween(1000),
        repeatMode = RepeatMode.Reverse
    )
)