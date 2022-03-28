import R.Graphics.characterR
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp

@Composable
fun onMove() {
    val stepSize = 50f
    val topSize = 0f
    var left by remember { mutableStateOf(stepSize) }
    var top by remember { mutableStateOf(topSize) }

    Box(Modifier.size(200.dp).padding(0.dp, 16.dp), Alignment.Center) {

        Column {
            Button(onClick = { top -= stepSize }) {
                Icon(Icons.Default.KeyboardArrowUp, "Up")
            }
            Spacer(Modifier.padding(20.dp))

            Button(onClick = { top += stepSize }) {
                Icon(Icons.Default.KeyboardArrowDown, "Down")
            }
        }
        Row {
            Button(onClick = { left -= stepSize }) {
                Icon(Icons.Default.KeyboardArrowLeft, "Left")
            }
            Spacer(Modifier.padding(20.dp))

            Button(onClick = { left += stepSize }) {
                Icon(Icons.Default.KeyboardArrowRight, "Right")
            }
        }
    }
    Canvas(Modifier.padding(0.dp, 350.dp)) {
        withTransform({ translate(left, top) }) {
            drawImage(image = characterR)
        }
    }
}

