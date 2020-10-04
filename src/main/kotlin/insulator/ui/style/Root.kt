package insulator.ui.style

import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.* // ktlint-disable no-wildcard-imports

val styles = arrayOf(Root::class, TextStyle::class, ButtonStyle::class)

class Root : Stylesheet() {
    init {
        root {
            font = Font.font("Helvetica", 10.0)
            backgroundColor = multi(Theme.backgroundColor)
            padding = box(1.em)
        }
    }
}

class Theme {
    companion object {
        val black = Color.BLACK
        val backgroundColor = Color.WHITE
        val mainColor = c("#FF9100")
        val mainColorDark = c("#D65400")
        val alertColor = c("#cc0016")
        val alertColorDark = c("#960017")
        val lightGray = c("#ccc")
        val darkGray = c("#666")
        val blueColor = Color.BLUE
    }
}
