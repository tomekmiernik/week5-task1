package com.example.week5.service;

import com.example.week5.model.Currency;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {
    private RestTemplate restTemplate;
    private final String API_URL = "http://api.nbp.pl/api/exchangerates/tables/A/";

    public GameService() {
        restTemplate = new RestTemplate();
    }

    public Currency getCurrency(){
        Currency[] currency = restTemplate.getForObject(API_URL, Currency[].class);
        return currency != null ? currency[0] : null;
    }
}
