package com.joebrooks.mapshotserver.opensource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/opensource")
public class OpenSourceController {

    @GetMapping
    public String openSource(){
        return "opensource/index";
    }
}
