package com.m520it.one2many;

import com.m520it.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * @author ：chengw
 * @date ：Created in 2022/2/24 22:25
 * @description：生产者
 * @modified By：
 * @version: $
 */
public class Producer {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //第一个参数:队列名 ,第二个参数,队列是否持久化,第三个参数,队列的连接是否独占,第四个参数,是否自动删除队列
        channel.queueDeclare("one2many", true, false, false, null);
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("","one2many",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    ("one2many发送消息,一个生产者和多个消费者"+i).getBytes());

        }
        RabbitMQConnectionUtil.closeConnection(channel,connection);
    }
}
