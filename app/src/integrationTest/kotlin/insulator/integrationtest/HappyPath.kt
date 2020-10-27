package insulator.integrationtest

import insulator.integrationtest.helpers.FxFixture
import insulator.integrationtest.helpers.click
import insulator.integrationtest.helpers.lookup
import insulator.integrationtest.helpers.lookupFirst
import insulator.kafka.model.Cluster
import insulator.ui.style.ButtonStyle.Companion.settingsButton
import insulator.ui.style.TextStyle.Companion.h1
import insulator.ui.style.TextStyle.Companion.h2
import insulator.ui.style.TextStyle.Companion.subTitle
import io.kotest.assertions.timing.eventually
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAtLeastOne
import io.kotest.matchers.shouldBe
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.stage.Window
import tornadofx.CssRule
import tornadofx.Stylesheet.Companion.button
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

@ExperimentalTime
class HappyPath : FreeSpec({
    "Happy path start the app and show list clusters view" - {
        FxFixture().use { fixture ->
            val clusters = (1..5).map { Cluster(name = "clusterName$it", endpoint = "endpoint$it") }.toTypedArray()
            fixture.startAppWithClusters(*clusters)

            "Title should be Clusters" {
                eventually(10.seconds) {
                    lookupFirst<Label>(h1).text shouldBe "Clusters"
                }
            }

            "All clusters config should be available with a settings button" {
                clusters.forEach {
                    val clusterId = CssRule.id("cluster-${it.guid}")
                    lookupFirst<Label>(clusterId.contains(h2)).text shouldBe it.name
                    lookupFirst<Label>(clusterId.contains(subTitle)).text shouldBe it.endpoint
                    lookupFirst<Button>(clusterId.contains(settingsButton))
                }
            }

            "There is a button to add new clusters" {
                lookupFirst<Button>(".button-bar .button").text shouldBe "Add new cluster"
            }

            "Open Add a new cluster windows" {
                lookupFirst<Button>(CssRule.id("button-bar").contains(button)).click()
                Window.getWindows().first()
                lookup<Label>(h1).forAtLeastOne { it.text shouldBe "Cluster connection" }
                lookup<Label>(h1).forAtLeastOne { it.text shouldBe "Schema registry" }
                lookup<Button>(button).forAtLeastOne { it.text shouldBe "Save" }
                // todo: complete
            }
        }
    }
})
