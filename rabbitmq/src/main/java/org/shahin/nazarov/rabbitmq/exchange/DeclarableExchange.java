package org.shahin.nazarov.rabbitmq.exchange;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public interface DeclarableExchange {
    AMQP.Exchange.DeclareOk declare(Channel channel, String exhangeName) throws IOException;
}
