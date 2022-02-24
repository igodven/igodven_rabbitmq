package com.m520it.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：chengw
 * @date ：Created in 2022/2/24 22:05
 * @description：获取rabbitmq连接工具类
 * @modified By：
 * @version: $
 */
public class RabbitMQConnectionUtil {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    static {

        connectionFactory.setHost("124.223.112.64");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("ems");
    }

    public static Connection getConnection() {

        try {
            Connection connection =connectionFactory.newConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
