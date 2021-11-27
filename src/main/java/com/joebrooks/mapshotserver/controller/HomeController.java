package com.joebrooks.mapshotserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(){
        return "PeVJt7WP3zB7RS-Mc5YEo_DtFfADq41jXfxkfLhXkbk";
    }
}
