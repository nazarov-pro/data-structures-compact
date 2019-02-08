package org.shahin.nazarov.edd.kafka.consume;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.Closeable;
import java.util.Properties;

public abstract class AbstractKafkaConsumer<K, V> implements Closeable {
    private String keySerializer;
    private String valueSerializer;
    private String bootstrapServer;
    private String groupId;
    private boolean autoCommit;
    private int autoCommitInterval;

    public AbstractKafkaConsumer(String keySerializer, String valueSerializer, String bootstrapServer, String groupId,
                                 boolean autoCommit, int autoCommitInterval) {
        this.keySerializer = keySerializer;
        this.valueSerializer = valueSerializer;
        this.bootstrapServer = bootstrapServer;
        this.groupId = groupId;
        this.autoCommit = autoCommit;
        this.autoCommitInterval = autoCommitInterval;
    }

    public AbstractKafkaConsumer(String keySerializer, String valueSerializer) {
        this(keySerializer, valueSerializer, "eventbased.eastus.cloudapp.azure.com:9092", "test",
                true, 1000);
    }

    protected KafkaConsumer<K, V> generateConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServer);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", autoCommit);
        props.put("auto.commit.interval.ms", autoCommitInterval);
        props.put("key.deserializer", keySerializer);
        props.put("value.deserializer", valueSerializer);
        return new KafkaConsumer(props);
    }
}
