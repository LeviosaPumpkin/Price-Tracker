package com.leviosa.pumpkin.pricetracker.domain;

import java.util.Map;
import javax.enterprise.inject.Produces;

public class PriceTrackerFactory {    
    private static Map<EPriceTracker, PriceTracker> priceTrackers = Map.of(
            EPriceTracker.YANDEX, new YandexRealtyPriceTracker(),
            EPriceTracker.CIAN, new CianPriceTracker()
        );

    @Produces
    public static PriceTracker createPriceTracker(EPriceTracker priceTracker) {
        return priceTrackers.get(priceTracker);
    }
}