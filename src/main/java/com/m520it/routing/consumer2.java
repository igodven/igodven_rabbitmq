package com.m520it.routing;

import com.m520it.utils.RabbitMQConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ：chengw
 * @date ：Created in 2022/2/24 22:39
 * @description：
 * @modified By：
 * @version: $
 */
public class consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机,,第二个参数,交换机的类型
        channel.exchangeDeclare("logs", BuiltinExchangeType.DIRECT);
        //随机获取一个队列名
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, "logs", "error");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消息费者2"+new String(body));
            }
        });
    }
}
