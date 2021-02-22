package com.leviosa.pumpkin.pricetracker.domain;

import java.util.List;

@PriceTrackerType(EPriceTracker.YANDEX)
public class YandexRealtyPriceTracker implements PriceTracker {
    private List<Long> ids;
   
    @Override
    public void getPrice() {
        System.out.println("Yandex");
        ids.forEach(System.out::println);
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