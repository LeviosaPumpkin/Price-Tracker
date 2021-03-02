package com.leviosa.pumpkin.pricetracker.domain;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface PriceTracker {
    default Map<String, Integer> getPrices() {
        return getIds().stream().collect(Collectors.toMap(id -> getUrl(id.toString()), this::getPrice));
    }
    List<Long> getIds();
    void setIds(List<Long> ids);
    Integer getPrice(Long id);
    default HttpResponse sendRequest(String id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(
                URI.create(getUrl(id)))
                .header("accept", "application/json")
                .build();
        return client.send(request,  HttpResponse.BodyHandlers.ofString());
    }
    String getUrl(String id);
}