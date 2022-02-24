package com.m520it.publish2subscribe;

import com.m520it.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * @author ：chengw
 * @date ：Created in 2022/2/24 22:35
 * @description：
 * @modified By：
 * @version: $
 */
public class producer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机,,第二个参数,交换机的类型
        channel.exchangeDeclare("pub2sub", BuiltinExchangeType.FANOUT);
        channel.basicPublish("pub2sub","",MessageProperties.PERSISTENT_TEXT_PLAIN,"交换机的方式发送消息,每个消费者都将收到消息".getBytes());
        RabbitMQConnectionUtil.closeConnection(channel, connection);
    }
}
