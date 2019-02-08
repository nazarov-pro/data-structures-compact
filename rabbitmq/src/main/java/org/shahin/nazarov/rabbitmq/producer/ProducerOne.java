package org.shahin.nazarov.rabbitmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.shahin.nazarov.rabbitmq.domain.JavaObject;
import org.shahin.nazarov.rabbitmq.util.Constants;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerOne {

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constants.hostname);
        try (Connection connection = factory.newConnection();Channel channel = connection.createChannel()) {

            ObjectMapper mapper = new ObjectMapper();
            channel.queueDeclare(Constants.queueOne, false, false, false, null);
            for(int i = 0; i < 1000; i++){
                JavaObject javaObject = new JavaObject();
                javaObject.setData("Test");
                javaObject.setIndex(i);
                channel.basicPublish("", Constants.queueOne, null, mapper.writeValueAsBytes(javaObject));
                System.out.println(" [x] Sent '" + javaObject.toString() + "'");
                Thread.sleep(100);
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
