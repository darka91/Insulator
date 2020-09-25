package insulator.lib.kafka

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import insulator.lib.jsonhelper.JsonToAvroConverter
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.Producer as KafkaProducer

interface Producer {
    fun validate(value: String, topic: String): Either<Throwable, Unit>
    fun produce(topic: String, key: String, value: String): Either<Throwable, Unit>
}

class AvroProducer(
    private val avroProducer: KafkaProducer<String, GenericRecord>,
    private val schemaRegistry: SchemaRegistry,
    private val jsonAvroConverter: JsonToAvroConverter
) : Producer {

    private val schemaCache = HashMap<String, Either<Throwable, String>>()

    override fun validate(value: String, topic: String) =
        internalValidate(value, topic).flatMap { Unit.right() }

    override fun produce(topic: String, key: String, value: String) =
        internalValidate(value, topic)
            .map { ProducerRecord(topic, key, it) }
            .flatMap { avroProducer.runCatching { send(it) }.fold({ Unit.right() }, { it.left() }) }

    private fun internalValidate(value: String, topic: String) =
        getCachedSchema(topic).flatMap { jsonAvroConverter.convert(value, it) }

    private fun getCachedSchema(topic: String) =
        schemaCache.getOrPut(
            topic,
            {
                schemaRegistry.getSubject("$topic-value")
                    .map { it.schemas.maxByOrNull { s -> s.version }?.schema }
                    .flatMap { it?.right() ?: Throwable("Schema not found").left() }
            }
        )
}

class StringProducer(private val stringProducer: KafkaProducer<String, String>) : Producer {
    override fun validate(value: String, topic: String) = Unit.right()
    override fun produce(topic: String, key: String, value: String): Either<Throwable, Unit> {
        val record = ProducerRecord(topic, key, value)
        return stringProducer.runCatching { send(record) }.fold({ Unit.right() }, { it.left() })
    }
}
