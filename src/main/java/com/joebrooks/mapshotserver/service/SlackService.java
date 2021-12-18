package com.joebrooks.mapshotserver.service;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class SlackService {

    private final String url;

    public SlackService(){
        this.url = System.getenv("SLACK_URL");
    }

    public void sendMessage(String message){
        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        rt.postForObject(url, message, String.class);
    }
}
