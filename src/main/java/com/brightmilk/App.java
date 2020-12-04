package com.brightmilk;

import com.brightmilk.Jobs.SimpleJob;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws SchedulerException
    {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                        .withIdentity("myJob", "myGroup")
                        .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity("myTrigger")
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                        .withIntervalInSeconds(3)
                                        .repeatForever()
                                        )
                        .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        scheduler.shutdown();
    }
}
