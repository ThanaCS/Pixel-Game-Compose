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
fun Move() {
    val stepSize = 20f
    var left by remember { mutableStateOf(0f) }
    var top by remember { mutableStateOf(0f) }
    var directions by remember { mutableStateOf(Direction.Right) }

    Character(left, top, directions)
    // TODO(Interact)
    Box(Modifier.size(200.dp).padding(0.dp, 16.dp), Alignment.Center) {
        Column {
            Button(onClick = {
                top -= stepSize
                directions = Direction.Up
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
}
