package com.leviosa.pumpkin.pricetracker.domain;

import java.util.List;

public interface PriceTracker {
    void getPrice();
    List<Long> getIds();
    void setIds(List<Long> ids);
}