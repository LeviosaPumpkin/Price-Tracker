package com.leviosa.pumpkin.pricetracker.domain;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@PriceTrackerType(EPriceTracker.CIAN)
public class CianPriceTracker implements PriceTracker {
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
            body = body.substring(body.indexOf("<span itemProp=\"price\" content=\""), body.indexOf(" ₽\">"));
            return Integer.parseInt(body.substring(body.lastIndexOf("\"") + 1).replaceAll(" ", ""));
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(YandexRealtyPriceTracker.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    @Override
    public String getUrl(String id) {
        return "https://www.cian.ru/sale/flat/" + id + "/";
    }
    
}