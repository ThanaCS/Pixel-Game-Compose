import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.GameTypography

@Composable
fun Interact(characterOffset: Offset, ghostOffset: Offset) {
    val radius = 70
    println(" characterOffset: $characterOffset  ghostOffset $ghostOffset")
    val messageState = remember { mutableStateOf(false) }

    if (characterOffset.x in (ghostOffset.x - radius)..(ghostOffset.x + radius) &&
        characterOffset.y in (ghostOffset.y - radius)..(ghostOffset.y + radius)
    ) {
        println("Near!!")
        messageState.value = true

    } else {
        println("Far")
        messageState.value = false
    }

    if (messageState.value) {
        Text(
            modifier = Modifier.padding(250.dp),
            text = "HI THERE",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = GameTypography.h6
        )
    }
}
