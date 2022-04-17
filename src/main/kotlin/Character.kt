import R.Graphics.characterL
import R.Graphics.characterR
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp

@Composable
fun Character(left: Float, top: Float, directions: Direction) {
    Canvas(Modifier.padding(0.dp, (Height * 0.4).dp)) {
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

