package com.example.week5.service;

import com.example.week5.model.Picture;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class PictureService {
    private RestTemplate restTemplate;
    private final String API_URL = "https://picsum.photos/v2/list?page=";
    private final String LIMIT_RESULT = "&limit=5";

    public PictureService() {
        restTemplate = new RestTemplate();
    }

    public Picture[] getPictureInfo() {
        return restTemplate.getForObject(
                API_URL
                        + randomPageNumber()
                        + LIMIT_RESULT,
                Picture[].class);
    }

    private int randomPageNumber() {
        Random random = new Random();
        return random.nextInt(9);
    }
}
