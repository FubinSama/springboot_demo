package demo.job;

import com.sun.org.apache.bcel.internal.generic.DADD;
import lombok.Setter;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 通过继承QuartzJobBean，并实现executeInternal方法来创建job
 */
public class MySecondJob extends QuartzJobBean {
    @Setter private String name;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hello:" + name + ":" + new Date());
    }
}
