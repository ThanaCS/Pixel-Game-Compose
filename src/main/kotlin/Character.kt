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
fun Character(left: Float, top: Float, directions: Direction) {
    var offset: Offset = Offset.Zero
    Column(
        Modifier
            .fillMaxSize()
            .offset(20.dp, 250.dp)
    ) {
        Canvas(Modifier.padding(0.dp)
            .onGloballyPositioned {
                val characterX = it.positionInRoot().x + left / characterL.width
                val characterY = it.positionInRoot().y + top / characterL.height
                offset = Offset(x = characterX, y = characterY)
                print(offset)
            }
        ) {
            translate(left, top) {
                when (directions) {
                    Direction.Left -> drawImage(image = characterL)
                    Direction.Right -> drawImage(image = characterR)
                    Direction.Up -> drawImage(image = characterR)
                    Direction.Down -> drawImage(image = characterL)
                }
            }
        }
    }
}

