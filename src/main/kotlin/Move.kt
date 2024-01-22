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
    var ghostOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    val requester = remember { FocusRequester() }

    val canMove = mutableMapOf(
        PlayerDirection.Left to true,
        PlayerDirection.Right to true,
        PlayerDirection.Up to true,
        PlayerDirection.Down to true,
    )

    val gridCol = 11
    val gridRow = 5
    val mapX = "-------*-------" +
            "----*--*-------" +
            "-----*------*--" +
            "---------*-----" +
            "-----*---------"


    val mapIndex = mapX.map { it }

    LazyVerticalGrid(
        cells = GridCells.Fixed(gridCol),
        contentPadding = PaddingValues(10.dp),
    ) {

        itemsIndexed(mapIndex) { index, item ->
            if (item == '*') {
                Box(
                    modifier = Modifier
                        .padding(4.dp),
                ) {
                    Ghost { offset ->
                        ghostOffset = offset
                    }

                }
            }
        }

    }

    if (ghostOffset != Offset(0f, 0f) && characterOffset != Offset(0f, 0f))
        Interact(characterOffset, ghostOffset) { isOverlapped ->
            if (isOverlapped) {
                when (directions) {
                    PlayerDirection.Left -> canMove.map {
                        canMove[it.key] = it.key != PlayerDirection.Left
                    }
                    PlayerDirection.Right -> canMove.map {
                        canMove[it.key] = it.key != PlayerDirection.Right
                    }
                    PlayerDirection.Up -> canMove.map { canMove[it.key] = it.key != PlayerDirection.Up }
                    PlayerDirection.Down -> canMove.map {
                        canMove[it.key] = it.key != PlayerDirection.Down
                    }
                }
            }
        }

    Character(left, top, directions) { offset -> characterOffset = offset }

    Box(
        Modifier
            .pointerInput(key1 = true) {
                detectTapGestures(onPress = {
                    requester.requestFocus()
                })
            }.focusRequester(requester)
            .focusable()
            .onKeyEvent {
                when (it.key) {
                    Key.DirectionUp -> {
                        if (canMove[PlayerDirection.Up] == true)
                            top -= stepSize
                        directions = PlayerDirection.Up
                        true
                    }
                    Key.DirectionDown -> {
                        if (canMove[PlayerDirection.Down] == true)
                            top += stepSize
                        directions = PlayerDirection.Down
                        true
                    }
                    Key.DirectionLeft -> {
                        if (canMove[PlayerDirection.Left] == true)
                            left -= stepSize
                        directions = PlayerDirection.Left
                        true
                    }
                    Key.DirectionRight -> {
                        if (canMove[PlayerDirection.Right] == true)
                            left += stepSize
                        directions = PlayerDirection.Right
                        true
                    }
                    else -> false
                }
            }
    )
    LaunchedEffect(Unit) {
        requester.requestFocus()
    }
}

