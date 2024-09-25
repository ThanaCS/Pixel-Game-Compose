import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import theme.GameTypography

@Composable
fun Interact(characterOffset: Offset, ghostOffset: Offset, onOverlapping: (Boolean) -> Unit) {
    onOverlapping(isNear(characterOffset, ghostOffset))
    if (isNear(characterOffset, ghostOffset)) {
        Box {
            Text(
                text = "Hi",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = GameTypography.h3
            )
        }
    }
}

fun isNear(characterOffset: Offset, otherObject: Offset, radius: Int = 50) =
    characterOffset.x in (otherObject.x - radius)..(otherObject.x + radius) &&
            characterOffset.y in (otherObject.y - radius)..(otherObject.y + radius)