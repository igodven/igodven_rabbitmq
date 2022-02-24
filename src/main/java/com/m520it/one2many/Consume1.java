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
public class Consume1 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //确认一个消息,消费一个消息,防止消息的丢失
        channel.basicQos(1);
        //管道绑定队列
        channel.queueDeclare("one2many", true, false, false, null);
        channel.basicConsume("one2many",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //手动确认消息
                channel.basicAck(envelope.getDeliveryTag(),false);
                System.out.println(new String(body));
            }
        });
    }
}
