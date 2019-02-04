package org.shahin.nazarov.rabbitmq.exchange;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class DirectExchange extends AbstractExchange {

    public static final BuiltinExchangeType EXCHANGE_TYPE = BuiltinExchangeType.DIRECT;

    @Override
    public AMQP.Exchange.DeclareOk declare(Channel channel, String exhangeName) throws IOException {
        return channel.exchangeDeclare(exhangeName, EXCHANGE_TYPE);
    }


}
