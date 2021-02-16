package com.leviosa.pumpkin.pricetracker;

import com.leviosa.pumpkin.pricetracker.jobs.PriceTrackerJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Application {

    public static void main(String args[]) {

        SchedulerFactory schedFact = new StdSchedulerFactory();
        try {

            Scheduler sched = schedFact.getScheduler();

            JobDetail job = JobBuilder
                    .newJob(PriceTrackerJob.class)
                    .withIdentity("priceTracker", "group")
                    .build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("priceTrackeJobTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder
                            .simpleSchedule()
                            .withIntervalInSeconds(600)
                            .repeatForever())
                    .build();

            sched.scheduleJob(job, trigger);
            sched.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}