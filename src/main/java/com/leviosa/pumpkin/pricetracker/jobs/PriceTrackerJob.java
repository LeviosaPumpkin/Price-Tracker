package com.leviosa.pumpkin.pricetracker.jobs;

import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.leviosa.pumpkin.pricetracker.domain.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.quartz.JobDataMap;

public class PriceTrackerJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        
        Map<EPriceTracker, PriceTracker> trackers = Map.of(
                //EPriceTracker.YANDEX, PriceTrackerFactory.createPriceTracker(EPriceTracker.YANDEX),
                EPriceTracker.CIAN, PriceTrackerFactory.createPriceTracker(EPriceTracker.CIAN)
        );
        //trackers.get(EPriceTracker.YANDEX).setIds(getIds(dataMap, "yandexIds"));
        trackers.get(EPriceTracker.CIAN).setIds(getIds(dataMap, "cianIds"));
        trackers
                .forEach((k,v) -> {
                    v.getPrices()
                            .entrySet()
                            .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
                });
        
    }
    
    private List<Long> getIds(JobDataMap dataMap, String priceTrackerType) {
        return Stream.of(dataMap.get(priceTrackerType)
                .toString()
                .split(","))
            .map(Long::parseLong)
            .collect(Collectors.toList());
    }
    
}