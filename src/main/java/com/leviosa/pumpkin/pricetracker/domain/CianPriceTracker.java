package com.leviosa.pumpkin.pricetracker.domain;

import java.util.List;

@PriceTrackerType(EPriceTracker.CIAN)
public class CianPriceTracker implements PriceTracker {
    private List<Long> ids;

    @Override
    public void getPrice() {
        System.out.println("cian tracker");
    }
    
    @Override
    public List<Long> getIds() {
        return ids;
    }
    
    @Override
    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
    
}