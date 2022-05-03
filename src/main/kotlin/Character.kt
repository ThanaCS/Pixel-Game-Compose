import R.Graphics.characterL
import R.Graphics.characterR
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.dp

@Composable
fun Character(left: Float, top: Float, directions: PlayerDirection, characterOffset: (Offset) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .offset(20.dp, 250.dp)
    ) {
        Canvas(Modifier.padding(0.dp)
            .onGloballyPositioned {
                val characterX = it.positionInRoot().x + left
                val characterY = it.positionInRoot().y + top
                val offset = Offset(x = characterX, y = characterY)
                characterOffset(offset)
            }
        ) {
            translate(left, top) {
                when (directions) {
                    PlayerDirection.Left -> drawImage(image = characterL)
                    PlayerDirection.Right -> drawImage(image = characterR)
                    PlayerDirection.Up -> drawImage(image = characterR)
                    PlayerDirection.Down -> drawImage(image = characterL)
                }
            }
        }
    }
}

