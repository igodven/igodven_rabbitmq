package com.m520it.one2one;

import com.m520it.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ：chengw
 * @date ：Created in 2022/2/24 22:19
 * @description：消息的消费者
 * @modified By：
 * @version: $
 */
public class Consume {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //管道绑定队列
        channel.queueDeclare("one2one", true, false, false, null);
        channel.basicConsume("one2one",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
