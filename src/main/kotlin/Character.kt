import R.Graphics.characterL
import R.Graphics.characterR
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.dp

@Composable
fun Character(left: Float, top: Float, directions: PlayerDirection, characterOffset: (Offset) -> Unit) {
    val imageAlpha = remember { Animatable(1f) }
    val previousDirection = remember { mutableStateOf(directions) }
    var characterX: Float
    var characterY: Float
    val transition = updateTransition(Offset(left, top))
    val animatedTopLeft by transition.animateOffset { targetValue ->
        targetValue
    }
    LaunchedEffect(directions) {
        if (previousDirection.value != directions) {
            imageAlpha.animateTo(0f, animationSpec = TweenSpec(20, easing = LinearOutSlowInEasing))
            previousDirection.value = directions
            imageAlpha.animateTo(1f, animationSpec = TweenSpec(20, easing = LinearOutSlowInEasing))
        }
    }
    Canvas(Modifier
        .fillMaxSize()
        .offset(20.dp, 250.dp)
        .onGloballyPositioned {
            characterX = it.positionInRoot().x + left + animatedTopLeft.x
            characterY = it.positionInRoot().y + top + animatedTopLeft.y
            val offset = Offset(x = characterX, y = characterY)
            characterOffset(offset)
        }
    ) {
        translate(left, top) {
            val characterImage: ImageBitmap = when (directions) {
                PlayerDirection.Left -> characterL
                PlayerDirection.Right -> characterR
                PlayerDirection.Up -> characterR
                PlayerDirection.Down -> characterL
            }
            drawImage(image = characterImage, alpha = imageAlpha.value, topLeft = animatedTopLeft)
        }
    }
}

