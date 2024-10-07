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
    val stepSize = 15f
    var left by remember { mutableStateOf(0f) }
    var top by remember { mutableStateOf(0f) }
    var directions by remember { mutableStateOf(PlayerDirection.Idle) }
    var characterOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    val requester = remember { FocusRequester() }
    val mapIndexes = remember { map1.map { it }.toMutableList() }
    val canMove = mutableMapOf<PlayerDirection, Boolean>().apply {
        PlayerDirection.values().forEach { put(it, true) }
    }
    var coinIndex by remember { mutableStateOf(-1) }
    Character(left, top, directions) { offset -> characterOffset = offset }

    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(50.dp),
    ) {
        itemsIndexed(mapIndexes) { index, item ->
            when (item) {
                Component.Ghost.value -> Ghost { offset ->
                    Interact(characterOffset, offset) { isOverlapped ->
                        if (isOverlapped) {
                            canMove.forEach { (key, _) ->
                                canMove[key] = key != directions
                            }
                        }
                    }
                }

                Component.Coin.value -> {
                    Coin { offset ->
                        if (isNear(characterOffset, offset)) {
                            coinIndex = index
                        }
                    }
                }

                Component.Tree.value -> {}

                Component.CollectedCoin.value -> {
                    Box(modifier = Modifier.size(20.dp)) // placeholder for the coin to maintain layout
                }
            }
        }
    }

    if (coinIndex > -1 && mapIndexes.isNotEmpty()) {
        mapIndexes.removeAt(coinIndex)
        mapIndexes.add(coinIndex, Component.CollectedCoin.value)
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

