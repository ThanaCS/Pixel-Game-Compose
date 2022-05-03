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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp


@Composable
fun Move() {
    val stepSize = 20f
    var left by remember { mutableStateOf(0f) }
    var top by remember { mutableStateOf(0f) }
    var directions by remember { mutableStateOf(PlayerDirection.Right) }
    var characterOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    var ghostOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    val canMove: MutableMap<PlayerDirection, Boolean> = mutableMapOf(
        PlayerDirection.Left to true,
        PlayerDirection.Right to true,
        PlayerDirection.Up to true,
        PlayerDirection.Down to true,
    )

    Ghost { offset ->
        ghostOffset = offset
    }

    Character(left, top, directions) { offset ->
        characterOffset = offset
    }

    if (ghostOffset != Offset(0f, 0f) && characterOffset != Offset(0f, 0f))
        Interact(characterOffset, ghostOffset) { isOverlapped ->
            if (isOverlapped) {
                when (directions) {
                    PlayerDirection.Left -> canMove.map { canMove[it.key] = it.key != PlayerDirection.Left }
                    PlayerDirection.Right -> canMove.map { canMove[it.key] = it.key != PlayerDirection.Right }
                    PlayerDirection.Up -> canMove.map { canMove[it.key] = it.key != PlayerDirection.Up }
                    PlayerDirection.Down -> canMove.map { canMove[it.key] = it.key != PlayerDirection.Down }
                }
            }
        }

    Box(Modifier.size(200.dp).padding(0.dp, 16.dp), Alignment.Center) {
        Column {
            Button(onClick = {
                if (canMove[PlayerDirection.Up] == true)
                    top -= stepSize
                directions = PlayerDirection.Up
            }) {
                Icon(Icons.Default.KeyboardArrowUp, null)
            }
            Spacer(Modifier.padding(20.dp))

            Button(onClick = {
                if (canMove[PlayerDirection.Down] == true)
                    top += stepSize
                directions = PlayerDirection.Down
            }) {
                Icon(Icons.Default.KeyboardArrowDown, null)
            }
        }
        Row {
            Button(onClick = {
                if (canMove[PlayerDirection.Left] == true)
                    left -= stepSize
                directions = PlayerDirection.Left
            }) {
                Icon(Icons.Default.KeyboardArrowLeft, null)
            }
            Spacer(Modifier.padding(20.dp))

            Button(onClick = {
                if (canMove[PlayerDirection.Right] == true)
                    left += stepSize
                directions = PlayerDirection.Right
            }) {
                Icon(Icons.Default.KeyboardArrowRight, null)
            }
        }
    }
}

