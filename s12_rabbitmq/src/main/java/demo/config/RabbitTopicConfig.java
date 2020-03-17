package demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {
    public static final String TOPIC_NAME="wfb-topic";

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_NAME, true, false);
    }

    @Bean
    Queue xiaomi() { //用来存储和xiaomi有关的消息
        return new Queue("xiaomi");
    }

    @Bean
    Queue huawei() { //用来存储和huawei有关的消息
        return new Queue("huawei");
    }

    @Bean
    Queue phone() { //用来存储和phone有关的消息
        return new Queue("phone");
    }

    @Bean
    Binding xiaomiBingding() { //凡是消息的routingkey以xiaomi开头，都被路由到名为xiaomi的Queue上
        return BindingBuilder.bind(xiaomi()).to(topicExchange()).with("xiaomi.#");
    }

    @Bean
    Binding huaweiBingding() { //凡是消息的routingkey以huawei开头，都被路由到名为huawei的Queue上
        return BindingBuilder.bind(huawei()).to(topicExchange()).with("huawei.#");
    }

    @Bean
    Binding phoneBingding() { //凡是消息的routingkey中间包含phone的，都被路由到名为phone的Queue上
        return BindingBuilder.bind(phone()).to(topicExchange()).with("#.phone.#");
    }
}
