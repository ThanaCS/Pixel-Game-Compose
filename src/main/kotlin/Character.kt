import R.Graphics.characterD1
import R.Graphics.characterD2
import R.Graphics.characterD3
import R.Graphics.characterD4
import R.Graphics.characterL1
import R.Graphics.characterL2
import R.Graphics.characterL3
import R.Graphics.characterL4
import R.Graphics.characterR1
import R.Graphics.characterR2
import R.Graphics.characterR3
import R.Graphics.characterR4
import R.Graphics.characterU1
import R.Graphics.characterU2
import R.Graphics.characterU3
import R.Graphics.characterU4
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Character(left: Float, top: Float, directions: PlayerDirection, characterOffset: (Offset) -> Unit) {
    var currentIndex by remember { mutableStateOf(0) }
    var characterImages by remember { mutableStateOf(emptyList<ImageBitmap>()) }

    characterImages = when (directions) {
        PlayerDirection.Left -> listOf(characterL1, characterL2, characterL3, characterL4)
        PlayerDirection.Right -> listOf(characterR1, characterR2, characterR3, characterR4)
        PlayerDirection.Up -> listOf(characterU1, characterU2, characterU3, characterU4)
        PlayerDirection.Down -> listOf(characterD1, characterD2, characterD3, characterD4)
        PlayerDirection.Idle -> listOf(if (characterImages.isNotEmpty()) characterImages.first() else characterD1)
    }

    LaunchedEffect(directions) {
        currentIndex = 0
        while (currentIndex < characterImages.size) {
            delay(80)
            currentIndex = (currentIndex + 1) % characterImages.size
        }
    }

    Canvas(modifier = Modifier.fillMaxSize().padding(0.dp).onGloballyPositioned {
        val characterX = it.positionInRoot().x + left
        val characterY = it.positionInRoot().y + top
        val offset = Offset(x = characterX, y = characterY)
        characterOffset(offset)
    }) {
        translate(left, top) {
            drawImage(characterImages[currentIndex])
        }
    }
}