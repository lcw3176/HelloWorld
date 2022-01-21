package com.joebrooks.mapshotserver.domain.map.kakao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public void errorTest(){
        throw new RuntimeException();
    }
}
