package insulator.styles

import insulator.ui.style.Theme
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.* // ktlint-disable no-wildcard-imports

class Controls : Stylesheet() {
    private val defaultPadding = 1.em

    companion object {

        // Components
        val sidebar by cssclass()
        val sidebarItem by cssclass()
    }

    init {

        sidebar {
            prefWidth = 250.0.px
            backgroundColor = multi(Theme.backgroundColor)
            backgroundColor = multi(Theme.backgroundColor)
            borderInsets = multi(box(-defaultPadding, 0.px, -defaultPadding, -defaultPadding))
            borderColor = multi(box(Color.TRANSPARENT, Theme.lightGray, Theme.lightGray, Theme.lightGray))
            padding = box(-defaultPadding, 0.px, 0.px, 0.px)
            alignment = Pos.TOP_CENTER
        }

        sidebarItem {
            and(hover) { backgroundColor = multi(Theme.mainColor) }
            minHeight = 50.0.px
            borderInsets = multi(box(-defaultPadding, 0.px, 0.px, -defaultPadding))
            padding = box(defaultPadding)
            alignment = Pos.CENTER_LEFT
            imageView { insets(0.0, 15.0, 0.0, 0.0) }
        }

        scrollPane {
            focusColor = Theme.backgroundColor
            focusColor = Theme.backgroundColor
            borderWidth = multi(box(0.0.px))
            borderColor = multi(box(Theme.backgroundColor))
            borderColor = multi(box(Theme.backgroundColor))
            backgroundInsets = multi(box(0.0.px))
            backgroundColor = multi(Theme.backgroundColor)
            backgroundColor = multi(Theme.backgroundColor)
            and(focused) { backgroundInsets = multi(box(0.0.px)) }
            viewport {
                borderColor = multi(box(Theme.backgroundColor))
                borderColor = multi(box(Theme.backgroundColor))
                backgroundInsets = multi(box(0.0.px))
                backgroundColor = multi(Theme.backgroundColor)
                backgroundColor = multi(Theme.backgroundColor)
            }
            corner {
                borderColor = multi(box(Theme.backgroundColor))
                borderColor = multi(box(Theme.backgroundColor))
                backgroundInsets = multi(box(0.0.px))
                backgroundColor = multi(Theme.backgroundColor)
                backgroundColor = multi(Theme.backgroundColor)
            }
        }

        dialogPane {
            padding = box(defaultPadding)
            headerPanel {
                borderColor = multi(box(Theme.backgroundColor))
                borderColor = multi(box(Theme.backgroundColor))
                backgroundColor = multi(Theme.backgroundColor)
                backgroundColor = multi(Theme.backgroundColor)
                backgroundInsets = multi(box(0.0.px))
                padding = box(0.0.px)
            }
            buttonBar {
                padding = box(0.0.px, (-10.0).px, 0.0.px, 0.0.px)
                cancel {
                    textFill = Theme.alertColor
                    and(hover) {
                        textFill = Theme.backgroundColor
                        backgroundColor = multi(Theme.alertColor)
                    }
                }
            }
            maxHeight = 140.0.px
            prefHeight = 140.0.px
            minHeight = 140.0.px
        }

        contextMenu {
            padding = box(0.px)
            minWidth = 100.0.px
            textFill = Color.BLACK
            menuItem {
                padding = box(10.0.px)
                and(focused) {
                    backgroundColor = multi(Theme.mainColor)
                    label {
                        textFill = Theme.backgroundColor
                        textFill = Theme.backgroundColor
                    }
                }
            }
        }
    }
}
