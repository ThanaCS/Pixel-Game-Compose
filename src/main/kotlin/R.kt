import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource

object R {
    object Graphics {
        val characterR by lazy {
            useResource("photos/characterR.png") { loadImageBitmap(it) }
        }
        val environment01 by lazy {
            useResource("photos/env01.png") { loadImageBitmap(it) }
        }
    }
}