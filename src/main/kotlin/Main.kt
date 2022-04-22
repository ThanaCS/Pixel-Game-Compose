import R.Graphics.environment01
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        resizable = false
    ) {
        gameTheme {
            Background()
            Move()
        }
    }
}

@Composable
fun Background() {
    Image(bitmap = environment01, null)
    Box(Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.BottomCenter).padding(60.dp),
            text = "LEVEL ONE",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = GameTypography.h3
        )
    }
}
