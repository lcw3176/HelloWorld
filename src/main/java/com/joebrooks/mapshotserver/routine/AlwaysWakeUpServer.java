package com.joebrooks.mapshotserver.routine;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AlwaysWakeUpServer {

//    private String myUrl = "https://mapshot.herokuapp.com";
//    private String myUrl = "http://localhost:8080";
//
//    @Scheduled(cron = "0 */10 * * * *")
//    public void preventSleepingHeroku(){
//        RestTemplate rt = new RestTemplate();
//        rt.getForEntity(myUrl, String.class);
//    }
}
