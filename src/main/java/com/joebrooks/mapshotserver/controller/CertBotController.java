package com.joebrooks.mapshotserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/.well-known/acme-challenge/4dzk68doGZKXGFZGbDnezMhDHpVd-rIK90JeiKB2CyY")
public class CertBotController {

    @GetMapping
    public String value(){
        return "4dzk68doGZKXGFZGbDnezMhDHpVd-rIK90JeiKB2CyY.gQsJ47naSi2zdq2RYb-lIdGLA7Laie_nXIV21JuqhMM";
    }

}
