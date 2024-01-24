import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.GameTypography

@Composable
fun Interact(characterOffset: Offset, ghostOffset: Offset, onOverlapping: (Boolean) -> Unit) {

    onOverlapping(isNear(characterOffset, ghostOffset))

    if (isNear(characterOffset, ghostOffset)) {
        Text(
            modifier = Modifier.padding(250.dp),
            text = "HI THERE",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = GameTypography.h6
        )
    }
}

fun isNear(characterOffset: Offset, otherObject: Offset, radius: Int = 50) =
    characterOffset.x in (otherObject.x - radius)..(otherObject.x + radius) &&
            characterOffset.y in (otherObject.y - radius)..(otherObject.y + radius)