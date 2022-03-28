import R.Graphics.environment01
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import theme.GameTypography
import theme.gameTheme

const val Height = 700
const val Width = 700

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Game 01",
        state = rememberWindowState(height = Height.dp, width = Width.dp),
    ) {
        gameTheme {
            Image(bitmap = environment01, "image", Modifier.fillMaxSize())
            onMove()
            Row(Modifier.fillMaxSize().padding(16.dp), Arrangement.Center) {
                Text(
                    text = "LEVEL ONE",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = GameTypography.h3,
                )
            }
        }
    }
}
