package demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitHeaderConfig {
    public static final String HEADER_NAME="wfb-header";

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(HEADER_NAME, true, false);
    }

    @Bean
    Queue queueName() {
        return new Queue("name-queue");
    }

    @Bean
    Queue queueAge() {
        return new Queue("age-queue");
    }

    @Bean
    Binding bindingName() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "wfb");
        return BindingBuilder.bind(queueName()).to(headersExchange()).whereAny(map).match();
    }

    @Bean
    Binding bindingAge() {
        return BindingBuilder.bind(queueAge()).to(headersExchange()).where("age").exists();
    }
}
