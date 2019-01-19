package org.shahin.nazarov.kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public class StringConsumer extends AbstractKafkaConsumer<String, String> {

    private KafkaConsumer<String, String> kafkaConsumer;

    public StringConsumer() {
        super("org.apache.kafka.common.serialization.StringDeserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer = generateConsumer();
    }

    public void subscribe(String... topics) {
        kafkaConsumer.subscribe(Arrays.asList(topics));
    }

    public ConsumerRecords<String, String> poll(int seconds){
        return kafkaConsumer.poll(Duration.ofSeconds(seconds));
    }

    @Override
    public void close() throws IOException {
        kafkaConsumer.close();
    }
}
