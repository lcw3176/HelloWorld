package com.joebrooks.mapshotserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("https://testservermapshot.netlify.app")
@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping
    public String welcomePage(){

        return "index";
    }
}
