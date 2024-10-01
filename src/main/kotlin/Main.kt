import R.Graphics.environment01
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
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
    Image(bitmap = environment01, null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
}
