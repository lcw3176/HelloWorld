package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.domain.KakaoMap;
import com.joebrooks.mapshotserver.util.CallableChromeDriver;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ChromeDriverService {


    private ExecutorService executorService;
    private CallableChromeDriver callableChromeDriver;

    public ChromeDriverService() throws Exception {
        executorService = Executors.newFixedThreadPool(4);
        callableChromeDriver = new CallableChromeDriver();
    }

    public byte[] getImage(KakaoMap kakaoMapInfo) throws Exception {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("mapshot.herokuapp.com")
                .path("/proxy/kakao/crawl")
                .queryParam("lat", kakaoMapInfo.getLat())
                .queryParam("lng", kakaoMapInfo.getLng())
                .queryParam("level", kakaoMapInfo.getLevel())
                .queryParam("type", kakaoMapInfo.getType())
                .build(true);

        callableChromeDriver.setKakaoMapInfo(uri);

        return  executorService.submit(callableChromeDriver).get();
    }


}
