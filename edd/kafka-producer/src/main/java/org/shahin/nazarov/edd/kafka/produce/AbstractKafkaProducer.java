package org.shahin.nazarov.edd.kafka.produce;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.Closeable;
import java.util.Properties;

public abstract class AbstractKafkaProducer<K, V> implements KafkaSender<V>, Closeable {
    private String keySerializer;
    private String valueSerializer;
    private String bootstrapServer;
    private String acks;
    private int retries;
    private int batchSize;
    private int linger;
    private int bufferSize;

    public AbstractKafkaProducer(String keySerializer, String valueSerializer, String bootstrapServer,
                                 String acks, int retries, int batchSize, int linger, int bufferSize) {
        this.keySerializer = keySerializer;
        this.valueSerializer = valueSerializer;
        this.bootstrapServer = bootstrapServer;
        this.acks = acks;
        this.retries = retries;
        this.batchSize = batchSize;
        this.linger = linger;
        this.bufferSize = bufferSize;
    }

    public AbstractKafkaProducer(String keySerializer, String valueSerializer) {
        this(keySerializer, valueSerializer, "eventbased.eastus.cloudapp.azure.com:9092", "all",
                0, 16384, 1, 33554432);
    }

    protected KafkaProducer<K, V> generateProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServer);
        props.put("acks", acks);
        props.put("retries", retries);
        props.put("batch.size", batchSize);
        props.put("linger.ms", linger);
        props.put("buffer.memory", bufferSize);
        props.put("key.serializer", keySerializer);
        props.put("value.serializer", valueSerializer);
        return new KafkaProducer(props);
    }

}
