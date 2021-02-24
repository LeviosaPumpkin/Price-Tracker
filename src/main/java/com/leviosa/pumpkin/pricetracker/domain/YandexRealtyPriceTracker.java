package com.leviosa.pumpkin.pricetracker.domain;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@PriceTrackerType(EPriceTracker.YANDEX)
public class YandexRealtyPriceTracker implements PriceTracker {
    private List<Long> ids;
   
    @Override
    public void getPrice() {
        try {
            // create a client
            HttpClient client = HttpClient.newHttpClient();
            
            // create a request
            var request = HttpRequest.newBuilder(
                    URI.create("https://realty.yandex.ru/offer/2888262821298199553/"))
                    .header("accept", "application/json")
                    .build();
            
            // use the client to send the request
            var response = client.send(request,  HttpResponse.BodyHandlers.ofString());
            
            // the response:
            String body = response.body();
            body = body.substring(body.indexOf("<h3 class=\"OfferBaseInfo__price\"><div class=\"Price\"><span class=\"price\">"), body.indexOf("₽</span><span></span></div></h3>"));
            Long price = Long.parseLong(body.substring(body.lastIndexOf(">")).replaceAll(" ", ""));
            System.out.println(body);
        } catch (IOException ex) {
            Logger.getLogger(YandexRealtyPriceTracker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(YandexRealtyPriceTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public static void main(String [] args) {
        YandexRealtyPriceTracker tracker = new YandexRealtyPriceTracker();
        tracker.getPrice();
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