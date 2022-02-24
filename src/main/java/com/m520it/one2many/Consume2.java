package com.m520it.one2many;

import com.m520it.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ：chengw
 * @date ：Created in 2022/2/24 22:27
 * @description：消费者1
 * @modified By：
 * @version: $
 */
public class Consume2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        //管道绑定队列
        channel.queueDeclare("one2many", true, false, false, null);
        channel.basicConsume("one2many",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                channel.basicAck(envelope.getDeliveryTag(),false);
                System.out.println(new String(body));
            }
        });
    }
}
