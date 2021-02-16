package com.leviosa.pumpkin.pricetracker.jobs;

import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.leviosa.pumpkin.pricetracker.domain.*;
import java.util.ArrayList;

public class PriceTrackerJob implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        List<PriceTracker> trackers = new ArrayList<>();
        //add trackers
        //run trackers
        //produce report
    }
    
}