import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource

object R {
    object Graphics {
        val characterR by lazy {
            useResource("photos/characterR.png") { loadImageBitmap(it) }
        }
        val characterL by lazy {
            useResource("photos/characterL.png") { loadImageBitmap(it) }
        }
        val environment01 by lazy {
            useResource("photos/env01.png") { loadImageBitmap(it) }
        }
        val coin by lazy {
            useResource("photos/coin.png") { loadImageBitmap(it) }
        }
        val characterR1 by lazy {
            useResource("photos/R1.png") { loadImageBitmap(it) }
        }
        val characterR2 by lazy {
            useResource("photos/R2.png") { loadImageBitmap(it) }
        }
        val characterR3 by lazy {
            useResource("photos/R1.png") { loadImageBitmap(it) }
        }
        val characterR4 by lazy {
            useResource("photos/R4.png") { loadImageBitmap(it) }
        }
        val characterL1 by lazy {
            useResource("photos/L1.png") { loadImageBitmap(it) }
        }
        val characterL2 by lazy {
            useResource("photos/L2.png") { loadImageBitmap(it) }
        }
        val characterL3 by lazy {
            useResource("photos/L1.png") { loadImageBitmap(it) }
        }
        val characterL4 by lazy {
            useResource("photos/L4.png") { loadImageBitmap(it) }
        }
        val characterU1 by lazy {
            useResource("photos/U1.png") { loadImageBitmap(it) }
        }
        val characterU2 by lazy {
            useResource("photos/U2.png") { loadImageBitmap(it) }
        }
        val characterU3 by lazy {
            useResource("photos/U3.png") { loadImageBitmap(it) }
        }
        val characterU4 by lazy {
            useResource("photos/U4.png") { loadImageBitmap(it) }
        }
        val characterD1 by lazy {
            useResource("photos/D1.png") { loadImageBitmap(it) }
        }
        val characterD2 by lazy {
            useResource("photos/D2.png") { loadImageBitmap(it) }
        }
        val characterD3 by lazy {
            useResource("photos/D3.png") { loadImageBitmap(it) }
        }
        val characterD4 by lazy {
            useResource("photos/D4.png") { loadImageBitmap(it) }
        }
    }
}