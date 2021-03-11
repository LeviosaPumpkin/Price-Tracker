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
                    .usingJobData("yandexIds", "5250032644525504257")
                    .usingJobData("cianIds", "234616139,234616123")
                    .build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("priceTrackeJobTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder
                            .simpleSchedule()
                            .withIntervalInSeconds(60)
                            .repeatForever())
                    .build();

            sched.scheduleJob(job, trigger);
            sched.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}