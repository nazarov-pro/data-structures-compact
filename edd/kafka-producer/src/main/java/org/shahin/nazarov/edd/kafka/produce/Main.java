package org.shahin.nazarov.edd.kafka.produce;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        try (StringProducer stringProducer = new StringProducer()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter topic name:");
            String topic = scanner.nextLine();
            System.out.printf("%s topic selected. Below all messages will be send.\n", topic);
            while (true) {
                String line = scanner.next();
                if (line.trim().equalsIgnoreCase("exit")) {
                    break;
                }
                stringProducer.send(topic, line).get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
