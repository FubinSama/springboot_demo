package demo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * springboot自带的调度器，只需导入spring-boot-starter-web即可
 */
@Component
public class MySchedule {

    @Scheduled(fixedDelay = 1000)
    public void fixedDelay() {
        //在当前任务执行结束1s后开启另一个任务，即再次执行
        System.out.println("fixedDelay：" + new Date());
    }

    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        // 在当前任务执行开始2s后开启另一个任务，即再次执行
        System.out.println("fixedRate：" + new Date());
    }

    @Scheduled(initialDelay = 1000, fixedRate = 2000)
    public void initialDelay() {
        //initialDelay=1000表示首次执行的延迟时间
        System.out.println("initialDelay：" + new Date());
    }

    @Scheduled(cron = "0 * * * * ?")
    public void cron() {
        // 该任务每分钟执行一次
        System.out.println("cron：" + new Date());
    }
}
