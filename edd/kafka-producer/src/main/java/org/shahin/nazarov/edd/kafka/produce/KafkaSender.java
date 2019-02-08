package org.shahin.nazarov.edd.kafka.produce;

import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

public interface KafkaSender<V> {
    Future<RecordMetadata> send(String topic, V message);
}
