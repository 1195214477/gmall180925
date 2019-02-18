package com.atguigu.gmall.gmallmqtest.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerTest {

    //发送消息
    public static void main(String[] args) throws JMSException {

        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.159.130:61616");

        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();

        //打开连接
        connection.start();

        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //创建队列
        //Queue queue = session.createQueue("atguigu");
        Queue queue = session.createQueue("order");

        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

        //创建消息监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                //判断消息
//                if (message instanceof TextMessage){
//                    try {
//                        String text = ((TextMessage) message).getText();
//                        System.out.println("text="+text);
//                    } catch (JMSException e) {
//                        e.printStackTrace();
//                    }
//                }
                if (message instanceof MapMessage){
                    try {
                        String orderId = ((MapMessage) message).getString("orderId");
                        String result = ((MapMessage) message).getString("result");
                        System.out.println(orderId + "\t" + result);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

}
