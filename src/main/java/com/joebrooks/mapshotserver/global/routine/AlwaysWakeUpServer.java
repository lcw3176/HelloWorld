package com.joebrooks.mapshotserver.global.routine;

import com.joebrooks.mapshotserver.global.common.UriInfo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class AlwaysWakeUpServer {

//    private final String myUrl = UriComponentsBuilder.newInstance()
//            .scheme(UriInfo.scheme.toString())
//            .host(UriInfo.host.toString())
//            .build().toString();
//
//    @Scheduled(cron = "0 */10 * * * *")
//    public void preventSleepingHeroku(){
//        RestTemplate rt = new RestTemplate();
//        rt.getForEntity(myUrl, String.class);
//    }
}
