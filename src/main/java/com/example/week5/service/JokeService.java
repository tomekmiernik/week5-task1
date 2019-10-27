package com.example.week5.service;

import com.example.week5.model.Joke;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {
    private RestTemplate restTemplate;
    private final String JOKE_URL = "https://official-joke-api.appspot.com/jokes/random";

    private JokeService(){
        restTemplate = new RestTemplate();
    }

    public Joke getJokeInfo(){
        return restTemplate.getForObject(JOKE_URL, Joke.class);
    }
}
