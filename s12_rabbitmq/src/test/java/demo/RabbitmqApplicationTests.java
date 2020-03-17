package demo;

import demo.config.RabbitFanoutConfig;
import demo.config.RabbitHeaderConfig;
import demo.config.RabbitTopicConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void directTest() {
        rabbitTemplate.convertAndSend("hello-queue", "hello direct!");
    }

    @Test
    public void fanoutTest() {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUT_NAME, null, "hello fanout!");
    }

    @Test
    public void topicTest() {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME, "xiaomi.news", "小米新闻..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME, "huawei.news", "华为新闻..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME, "xiaomi.phone", "小米手机..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME, "phone.news", "手机新闻..");
    }

    @Test
    public void headerTest() {
        Message nameMsg = MessageBuilder
                .withBody("hello header! name-queue".getBytes())
                .setHeader("name", "wfb").build();
        Message ageMsg = MessageBuilder
                .withBody("hello header! age-queue".getBytes())
                .setHeader("age", "99").build();
        rabbitTemplate.send(RabbitHeaderConfig.HEADER_NAME, null, nameMsg);
        rabbitTemplate.send(RabbitHeaderConfig.HEADER_NAME, null, ageMsg);
    }
}
