package org.shahin.nazarov.kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try (StringConsumer consumer = new StringConsumer()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter topic name:");
            String topic = scanner.nextLine();
            consumer.subscribe(topic);
            System.out.printf("%s topic subscribed. All messages will show below.\n", topic);
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(5);
                consumerRecords.forEach(poll ->
                        System.out.println("offset = " + poll.offset() + ", value = " + poll.value())
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
