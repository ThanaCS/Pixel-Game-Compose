import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun Move() {
    val stepSize = 30f
    var left by remember { mutableStateOf(0f) }
    var top by remember { mutableStateOf(0f) }
    var directions by remember { mutableStateOf(PlayerDirection.Idle) }
    var characterOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    val requester = remember { FocusRequester() }
    val mapIndexes = map1.map { it }.toMutableList()
    val canMove = mutableMapOf<PlayerDirection, Boolean>().apply {
        PlayerDirection.values().forEach { put(it, true) }
    }
    Character(left, top, directions) { offset -> characterOffset = offset }

    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        itemsIndexed(mapIndexes) { _, item ->
            when (item) {
                Component.Ghost.value -> Ghost { offset ->
                    Interact(characterOffset, offset) { isOverlapped ->
                        if (isOverlapped) {
                            when (directions) {
                                PlayerDirection.Left -> canMove.map { canMove[it.key] = it.key != PlayerDirection.Left }
                                PlayerDirection.Right -> canMove.map {
                                    canMove[it.key] = it.key != PlayerDirection.Right
                                }
                                PlayerDirection.Up -> canMove.map { canMove[it.key] = it.key != PlayerDirection.Up }
                                PlayerDirection.Down -> canMove.map { canMove[it.key] = it.key != PlayerDirection.Down }
                                else -> {}
                            }
                        }
                    }
                }

                Component.Coin.value -> {
                    Coin {}
                }

                Component.Tree.value -> {}
            }
        }
    }

    Box(
        Modifier
            .pointerInput(key1 = true) {
                detectTapGestures(onPress = {
                    requester.requestFocus()
                })
            }
            .focusRequester(requester)
            .focusable()
            .onKeyEvent { event ->
                when (event.key) {
                    Key.DirectionUp -> {
                        if (event.type == KeyEventType.KeyDown && canMove[PlayerDirection.Up] == true && top > 0) {
                            top -= stepSize
                            directions = PlayerDirection.Up
                            return@onKeyEvent true
                        }
                    }
                    Key.DirectionDown -> {
                        if (event.type == KeyEventType.KeyDown && canMove[PlayerDirection.Down] == true && top < Height - 50) {
                            top += stepSize
                            directions = PlayerDirection.Down
                            return@onKeyEvent true
                        }
                    }
                    Key.DirectionLeft -> {
                        if (event.type == KeyEventType.KeyDown && canMove[PlayerDirection.Left] == true && left > 0) {
                            left -= stepSize
                            directions = PlayerDirection.Left
                            return@onKeyEvent true
                        }
                    }
                    Key.DirectionRight -> {
                        if (event.type == KeyEventType.KeyDown && canMove[PlayerDirection.Right] == true && left < Width - 50) {
                            left += stepSize
                            directions = PlayerDirection.Right
                            return@onKeyEvent true
                        }
                    }
                    else -> {}
                }

                if (event.type == KeyEventType.KeyUp) {
                    directions = PlayerDirection.Idle
                }
                false
            }
    )

    LaunchedEffect(Unit) {
        requester.requestFocus()
    }
}

