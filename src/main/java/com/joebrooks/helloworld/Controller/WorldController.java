package com.joebrooks.helloworld.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth/world")
public class WorldController {

    @GetMapping
    public String showForm(){
        return "world";
    }
}
