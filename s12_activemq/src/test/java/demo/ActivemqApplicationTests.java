package demo;

import demo.bean.JmsComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import demo.pojo.Message;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqApplicationTests {
    @Autowired JmsComponent jmsComponent;

    @Test
    public void contextLoads() {
        Message message = new Message();
        message.setContent("hello jms!");
        message.setDate(new Date());
        jmsComponent.send(message);
    }
}
