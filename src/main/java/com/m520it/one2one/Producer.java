package com.m520it.one2one;

import com.m520it.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * @author ：chengw
 * @date ：Created in 2022/2/24 22:04
 * @description：这是消息的生产者
 * @modified By：
 * @version: $
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //第一个参数:队列名 ,第二个参数,队列是否持久化,第三个参数,队列的连接是否独占,第四个参数,是否自动删除队列
        channel.queueDeclare("one2one", true, false, false, null);
        channel.basicPublish("","one2one",MessageProperties.PERSISTENT_TEXT_PLAIN,"one2one发送消息,只有一个生产者和一个消费者".getBytes());
        RabbitMQConnectionUtil.closeConnection(channel,connection);
    }
}
