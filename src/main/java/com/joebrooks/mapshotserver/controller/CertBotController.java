package com.joebrooks.mapshotserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/.well-known/acme-challenge")
public class CertBotController {

    @GetMapping
    public String bot(){
        return "bvC_ZlyQ3gIsRHOcpnVgnQu6pvIAeM651S-uw4CPI68";
    }
}
