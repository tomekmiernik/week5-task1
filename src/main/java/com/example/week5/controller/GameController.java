package com.example.week5.controller;

import com.example.week5.model.Currency;
import com.example.week5.model.Rate;
import com.example.week5.service.GameService;
import com.example.week5.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class GameController {

    private GameService gameService;
    private Rate rate;
    private int counter;
    private double priceValue;
    private String PATH_VIEW = "game/game";

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
        getRandomCurrencyRate();
    }

    @GetMapping("/game")
    public String getGamePage(Model model) {
        counter = 0;
        model.addAttribute("counter", counter);
        model.addAttribute("rate", rate);
        model.addAttribute("formAction", "check");
        return PATH_VIEW;
    }

    @GetMapping("/change")
    public String changeCurrency(Model model) {
        counter = 0;
        model.addAttribute("counter", counter);
        model.addAttribute("rate", getRandomCurrencyRate());
        model.addAttribute("formAction", "check");
        return PATH_VIEW;
    }

    @GetMapping("/check")
    public String submitAnswer(@RequestParam(value = "answer", required = false) String answer, Model model) {
        model.addAttribute("rate", rate);
        return validateUserAnswer(answer, model);
    }

    private Rate getRandomCurrencyRate() {
        Currency currency = gameService.getCurrency();
        Random random = new Random();
        rate = currency.getRates().get(random.nextInt(currency.getRates().size()));
        return rate;
    }

    private String validateUserAnswer(String answer, Model model) {
        if (answer.isEmpty()) {
            counter++;
            model.addAttribute("info", Messages.NOT_A_VALUE);
            return PATH_VIEW;
        }
        double tempValue;
        try {
            String convert = answer.replace(",", ".");
            tempValue = Double.parseDouble(convert);
            roundRatesValue();
        } catch (NumberFormatException nfe) {
            counter++;
            model.addAttribute("info", Messages.NOT_A_NUMBER);
            return PATH_VIEW;
        }
        checkUserAnswer(model, tempValue);
        model.addAttribute("counter", counter);
        return PATH_VIEW;
    }

    private void checkUserAnswer(Model model, double value) {
        if (value > priceValue) {
            counter++;
            model.addAttribute("info", Messages.A_LOT);
            model.addAttribute("formAction", "check");
        } else if (value < priceValue) {
            counter++;
            model.addAttribute("info", Messages.TO_LITTLE);
            model.addAttribute("formAction", "check");
        } else {
            counter++;
            model.addAttribute("info", Messages.CONGRATULATION);
            model.addAttribute("formAction", "game");
        }
    }

    private void roundRatesValue() {
        priceValue = Math.round(rate.getMid() * 100);
        priceValue /= 100;
    }
}
