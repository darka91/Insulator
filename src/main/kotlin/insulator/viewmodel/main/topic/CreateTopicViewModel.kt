package insulator.viewmodel.main.topic

import arrow.core.Option
import insulator.lib.kafka.AdminApi
import insulator.lib.kafka.model.Topic
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

class CreateTopicViewModel(cluster: CreateTopicModel = CreateTopicModel()) : ItemViewModel<CreateTopicModel>(cluster) {

    val admin: AdminApi by di()

    val nameProperty = bind { item?.nameProperty }
    val partitionCountProperty = bind { item?.partitionCountProperty }
    val replicationFactorProperty = bind { item?.replicationFactorProperty }

    fun save(): Option<Throwable> = admin.createTopics(this.item.toTopic()).get()
        .fold({ Option.just(it) }, { Option.empty() })
}

class CreateTopicModel(topic: Topic? = null) {
    val nameProperty = SimpleStringProperty(topic?.name)
    val partitionCountProperty = SimpleIntegerProperty(topic?.partitionCount ?: 0)
    val replicationFactorProperty = SimpleIntegerProperty(topic?.replicationFactor?.toInt() ?: 0)

    fun toTopic() = Topic(
        name = nameProperty.value,
        partitionCount = partitionCountProperty.value,
        replicationFactor = replicationFactorProperty.value.toShort(),
        isInternal = null,
        messageCount = null
    )
}
