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
import androidx.compose.ui.unit.dp


@Composable
fun onMove() {
    val stepSize = 50f
    val topSize = 0f
    var left by remember { mutableStateOf(stepSize) }
    var top by remember { mutableStateOf(topSize) }
    var directions by remember { mutableStateOf(Direction.Right) }

    Box(Modifier.size(200.dp).padding(0.dp, 16.dp), Alignment.Center) {
        Column {
            Button(onClick = {
                directions = Direction.Up
                top -= stepSize
            }) {
                Icon(Icons.Default.KeyboardArrowUp, null)
            }
            Spacer(Modifier.padding(20.dp))

            Button(onClick = {
                top += stepSize
                directions = Direction.Down
            }) {
                Icon(Icons.Default.KeyboardArrowDown, null)
            }
        }
        Row {
            Button(onClick = {
                left -= stepSize
                directions = Direction.Left
            }) {
                Icon(Icons.Default.KeyboardArrowLeft, null)
            }
            Spacer(Modifier.padding(20.dp))

            Button(onClick = {
                left += stepSize
                directions = Direction.Right
            }) {
                Icon(Icons.Default.KeyboardArrowRight, null)
            }
        }
    }
    character(left, top, directions)
}
