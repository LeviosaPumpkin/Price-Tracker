package com.leviosa.pumpkin.pricetracker.domain;

@PriceTrackerType(EPriceTracker.YANDEX)
public class YandexRealtyPriceTracker implements PriceTracker {
    @Override
    public void getPrice() {
        System.out.println("yandex tracker");
    } 
}