package org.shahin.nazarov.edd.kafka.produce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.IOException;
import java.util.concurrent.Future;

public class StringProducer extends AbstractKafkaProducer<String, String> {

    private final KafkaProducer<String, String> kafkaProducer;

    public StringProducer() {
        super("org.apache.kafka.common.serialization.StringSerializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = super.generateProducer();
    }

    @Override
    public Future<RecordMetadata> send(String topic, String message) {
        return kafkaProducer.send(new ProducerRecord(topic, message), (r, e) -> {
            e.printStackTrace();
        });
    }

    @Override
    public void close() throws IOException {
        kafkaProducer.close();
    }
}
