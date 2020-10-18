package insulator

import insulator.di.DIContainer
import insulator.ui.style.AppBarStyle
import insulator.ui.style.ButtonStyle
import insulator.ui.style.CheckBoxStyle
import insulator.ui.style.ComboBoxStyle
import insulator.ui.style.DialogPaneStyle
import insulator.ui.style.ListViewStyle
import insulator.ui.style.MainViewStyle
import insulator.ui.style.Root
import insulator.ui.style.ScrollBarStyle
import insulator.ui.style.ScrollPaneStyle
import insulator.ui.style.TableViewStyle
import insulator.ui.style.TextStyle
import insulator.views.configurations.ListClusterView
import javafx.stage.Stage
import tornadofx.* // ktlint-disable no-wildcard-imports

class Insulator : App(
    NoPrimaryViewSpecified::class,
    Root::class,
    AppBarStyle::class,
    ButtonStyle::class,
    CheckBoxStyle::class,
    ComboBoxStyle::class,
    ListViewStyle::class,
    TableViewStyle::class,
    TextStyle::class,
    DialogPaneStyle::class,
    ScrollPaneStyle::class,
    MainViewStyle::class,
    ScrollBarStyle::class
) {
    override fun start(stage: Stage) {
        super.start(stage)
        val view = ListClusterView()
        stage.scene = createPrimaryScene(view)
        FX.applyStylesheetsTo(stage.scene)
        stage.show()
        view.onDock()
    }
}

fun main(args: Array<String>) {
    FX.dicontainer = DIContainer()
    runCatching { launch<Insulator>(args) }
}
