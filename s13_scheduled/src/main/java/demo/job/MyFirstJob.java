package demo.job;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 通过添加@Component注解，将之注册到Spring容器，从而作为Job
 */
@Component
public class MyFirstJob {
    public void sayHello() {
        System.out.println("MyFirstJob:sayHello:" + new Date());
    }
}