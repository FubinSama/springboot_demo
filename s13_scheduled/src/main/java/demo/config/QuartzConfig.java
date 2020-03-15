package demo.config;

import demo.job.MySecondJob;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

@Configuration
public class QuartzConfig {
    /**
     * 通过MethodInvokingJobDetailFactoryBean类配置JobDetail，注册这种方法无法在创建JobDetail时传递参数
     * @return
     */
    @Bean
    MethodInvokingJobDetailFactoryBean jobDetail1() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("myFirstJob");
        bean.setTargetMethod("sayHello");
        return bean;
    }

    /**
     * 通过JobDetail配置JobDetail
     * @return
     */
    @Bean
    JobDetailFactoryBean jobDetail2() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MySecondJob.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", "wfb");
        bean.setJobDataMap(jobDataMap);
        bean.setDurability(true);
        return bean;
    }

    /**
     * 创建Trigger的简单实现
     * @return
     */
    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setJobDetail(jobDetail1().getObject());
        bean.setRepeatCount(3);
        bean.setStartDelay(1000);
        bean.setRepeatInterval(2000);
        return bean;
    }

    /**
     * 创建Trigger的cron实现
     * @return
     */
    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetail2().getObject());
        bean.setCronExpression("* * * * * ?");
        return bean;
    }

    /**
     * 配置Trigger，创建Scheduler
     * @return
     */
    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        SimpleTrigger simpleTrigger = simpleTriggerFactoryBean().getObject();
        CronTrigger cronTrigger = cronTriggerFactoryBean().getObject();
        bean.setTriggers(simpleTrigger, cronTrigger);
        return bean;
    }
}
