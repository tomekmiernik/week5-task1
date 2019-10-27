package com.example.week5.controller;

import com.example.week5.model.Joke;
import com.example.week5.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JokeController {

    private JokeService jokeService;
    private String setupJoke;
    private String punchlineJoke;

    @Autowired
    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping("/joke")
    public String homePage(Model model) {
        model.addAttribute("setup", setupJoke);
        return "jokes/joke";
    }

    @GetMapping("/answer")
    public String getAnswerPage(Model model) {
        model.addAttribute("setup", setupJoke);
        model.addAttribute("punchline", punchlineJoke);
        return "jokes/answer";
    }

    @GetMapping("/next")
    public String getNextQuestionPage() {
        setJokeInfo();
        return "redirect:/joke";
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setJokeInfo() {
        Joke joke = jokeService.getJokeInfo();
        setupJoke = joke.getSetup();
        punchlineJoke = joke.getPunchline();
    }
}
