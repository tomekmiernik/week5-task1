package com.example.week5.controller;

import com.example.week5.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PictureController {
    private PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/pictures")
    public String getPageWithPictureInfo(Model model) {
        model.addAttribute("pictures", pictureService.getPictureInfo());
        return "pictures/pictures";
    }
}
