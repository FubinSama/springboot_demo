package demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import demo.pojo.Message;

import javax.jms.Queue;

@Component
public class JmsComponent {
    @Autowired JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired Queue queue;

    public void send(Message msg) {
        jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

    @JmsListener(destination = "amq")
    public void receive(Message msg) {
        System.out.println("receive:" + msg);
    }
}
