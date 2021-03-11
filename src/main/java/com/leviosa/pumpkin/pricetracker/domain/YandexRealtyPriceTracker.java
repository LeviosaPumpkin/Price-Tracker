package com.leviosa.pumpkin.pricetracker.domain;

import java.io.IOException;
import java.net.http.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@PriceTrackerType(EPriceTracker.YANDEX)
public class YandexRealtyPriceTracker implements PriceTracker {
    private List<Long> ids;
    
    @Override
    public List<Long> getIds() {
        return ids;
    }
    
    @Override
    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
    
    @Override
    public Integer getPrice(Long id) {
        try {
            HttpResponse response = sendRequest(id.toString());
            String body = (String) response.body();
            body = body.substring(body.indexOf("<h3 class=\"OfferBaseInfo__price\"><div class=\"Price\"><span class=\"price\">"), body.indexOf("₽</span><span></span></div></h3>"));
            return Integer.parseInt(body.substring(body.lastIndexOf(">")).replaceAll(" ", ""));
        } catch (IOException | InterruptedException | StringIndexOutOfBoundsException ex) {
            Logger.getLogger(YandexRealtyPriceTracker.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    @Override
    public String getUrl(String id) {
        return "https://realty.yandex.ru/offer/" + id + "/";
    }
}