package com.atguigu.gmall.gmallmqtest.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class ProduceTest {

    //发送消息
    public static void main(String[] args) throws JMSException {

        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://192.168.159.130:61616");

        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();

        //打开连接
        connection.start();

        //创建session
        //boolean:是否用事务
        //int:
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //创建队列
        //Queue queue = session.createQueue("atguigu");
        Queue queue = session.createQueue("order");

        //创建生产者
        MessageProducer producer = session.createProducer(queue);

        //创建发送对象
        //ActiveMQTextMessage activeMQTextMessage = new ActiveMQTextMessage();
        //activeMQTextMessage.setText("饿了吗");

        ActiveMQMapMessage activeMQMapMessage = new ActiveMQMapMessage();
        activeMQMapMessage.setString("orderId","10001");
        activeMQMapMessage.setString("result","success");


        //发送消息
        //producer.send(activeMQTextMessage);
        producer.send(activeMQMapMessage);

        //关闭
        producer.close();
        session.close();
        connection.close();



    }

}
