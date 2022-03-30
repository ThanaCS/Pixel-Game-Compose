import R.Graphics.characterR
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp

@Composable
fun character(left: Float, top: Float) {
    Canvas(Modifier.padding(0.dp, (Height * 0.4).dp)) {
        translate(left, top) {
            drawImage(image = characterR)
        }
    }
}

