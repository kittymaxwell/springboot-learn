package springbootlearn.springbootredis.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TopicReceiver {

    @RabbitHandler
    @RabbitListener(queues = "topic.man")
    public void process(Map testMessage){
        System.out.println("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }


    @RabbitHandler
    @RabbitListener(queues = "topic.woman")
    public void processWoman(Map testMessage){
        System.out.println("TopicWomanReceiver消费者收到消息  : " + testMessage.toString());

    }
}
