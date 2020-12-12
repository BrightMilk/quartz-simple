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

public class App {
    public static void main(String[] args) throws SchedulerException
    {
        // Run Schedule Factory
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        // Extract scheduler from Schedule Factory
        Scheduler scheduler = schedulerFactory.getScheduler();
        
        /**
         * Run JobDetail with the job name, 
         * job group and class of the executing job
         */
        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                        .withIdentity("simpleJob", "simpleGroup")
                        .build();

        // Run SimpleTrigger with the trigger name and group name
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity("simpleTrigger")
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                        .withIntervalInSeconds(3)
                                        .repeatForever()
                                        )
                        .build();

        // Plan a task with JobDetail and Trigger
        scheduler.scheduleJob(job, trigger);
        // Run a scheduler
        scheduler.start();
    }
}
