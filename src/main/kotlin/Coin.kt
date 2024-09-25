import R.Graphics.coin
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp


@Composable
fun Coin(modifier: Modifier = Modifier, coinOffset: @Composable (Offset) -> Unit): Modifier {
    val infiniteTransition = rememberInfiniteTransition()
    var coinX by remember { mutableStateOf(0f) }
    var coinY by remember { mutableStateOf(0f) }
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = FastOutLinearInEasing,
            ),
        )
    )

    Image(coin, null, modifier = modifier.size(20.dp).graphicsLayer {
        this.rotationY = rotation
    }.onGloballyPositioned {
        coinX = it.positionInRoot().x
        coinY = it.positionInRoot().y
    })
    coinOffset(Offset(coinX, coinY))
    return modifier
}
