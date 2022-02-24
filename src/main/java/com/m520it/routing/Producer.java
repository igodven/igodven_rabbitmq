package com.m520it.routing;

import com.m520it.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * @author ：chengw
 * @date ：Created in 2022/2/24 22:48
 * @description：
 * @modified By：
 * @version: $
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机,,第二个参数,交换机的类型
        channel.exchangeDeclare("logs", BuiltinExchangeType.DIRECT);
        channel.basicPublish("logs","error", MessageProperties.PERSISTENT_TEXT_PLAIN,
                "更具路由key进行消息的发送".getBytes());
        RabbitMQConnectionUtil.closeConnection(channel, connection);
    }
}
