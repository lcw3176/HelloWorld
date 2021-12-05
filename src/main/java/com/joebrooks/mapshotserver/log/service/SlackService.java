package com.joebrooks.mapshotserver.log.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SlackService {

    @Value("${slack.url}")
    private String url;


    public void sendMesseage(String message){
        RestTemplate rt = new RestTemplate();
        rt.postForObject(url, message, String.class);
    }
}
