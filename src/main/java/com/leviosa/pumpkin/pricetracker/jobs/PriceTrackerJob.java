package com.leviosa.pumpkin.pricetracker.jobs;

import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.leviosa.pumpkin.pricetracker.domain.*;

public class PriceTrackerJob implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        List<PriceTracker> trackers = List.of(
                PriceTrackerFactory.createPriceTracker(EPriceTracker.YANDEX),
                PriceTrackerFactory.createPriceTracker(EPriceTracker.CIAN)
        );
        trackers.forEach(PriceTracker::getPrice);
        //produce report
    }
    
}