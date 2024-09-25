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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun Move() {
    val stepSize = 10f
    var left by remember { mutableStateOf(0f) }
    var top by remember { mutableStateOf(0f) }
    var directions by remember { mutableStateOf(PlayerDirection.Right) }
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

    Box(Modifier.pointerInput(key1 = true) {
        detectTapGestures(onPress = {
            requester.requestFocus()
        })
    }.focusRequester(requester).focusable().onKeyEvent {
        when (it.key) {
            Key.DirectionUp -> {
                if (canMove[PlayerDirection.Up] == true) top -= stepSize
                directions = PlayerDirection.Up
                true
            }
            Key.DirectionDown -> {
                if (canMove[PlayerDirection.Down] == true) top += stepSize
                directions = PlayerDirection.Down
                true
            }
            Key.DirectionLeft -> {
                if (canMove[PlayerDirection.Left] == true) left -= stepSize
                directions = PlayerDirection.Left
                true
            }
            Key.DirectionRight -> {
                if (canMove[PlayerDirection.Right] == true) left += stepSize
                directions = PlayerDirection.Right
                true
            }
            else -> false
        }
    })
    LaunchedEffect(Unit) {
        requester.requestFocus()
    }
}

