package util

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot

@Composable
fun Modifier.getComponentOffset(componentOffset: @Composable (Offset) -> Unit): Modifier {
    var ghostX by remember { mutableStateOf(0f) }
    var ghostY by remember { mutableStateOf(0f) }

    this.onGloballyPositioned {
        ghostX = it.positionInRoot().x
        ghostY = it.positionInRoot().y
    }

    componentOffset(Offset(ghostX, ghostY))

    return this
}