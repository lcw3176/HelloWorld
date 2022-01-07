package com.joebrooks.mapshotserver.domain.map.kakao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(".well-known/acme-challenge")
public class TestController {

    @GetMapping
    public String good(){
        return "R0LsHBLWtgZLSk-kBv0qf3BT_JXk50XJ-8ev6S2yxgw";
    }
}
