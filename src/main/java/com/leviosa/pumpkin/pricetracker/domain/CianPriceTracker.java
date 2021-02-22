package com.leviosa.pumpkin.pricetracker.domain;

@PriceTrackerType(EPriceTracker.CIAN)
public class CianPriceTracker implements PriceTracker {

    @Override
    public void getPrice() {
        System.out.println("cian tracker");
    }
    
}