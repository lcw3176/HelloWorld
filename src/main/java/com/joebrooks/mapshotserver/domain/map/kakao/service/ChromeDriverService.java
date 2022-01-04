package com.joebrooks.mapshotserver.domain.map.kakao.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joebrooks.mapshotserver.domain.map.kakao.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.map.kakao.util.CustomChromeDriver;
import com.joebrooks.mapshotserver.global.model.UriInfo;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChromeDriverService {
    private boolean available = true;

    public Optional<byte[]> getImage(KakaoMap kakaoMapInfo) {
        CustomChromeDriver driver = null;

        try{
            available = false;

            UriComponents uri = UriComponentsBuilder.newInstance()
                    .scheme(UriInfo.scheme.toString())
                    .host(UriInfo.host.toString())
                    .path(UriInfo.kakaoMapCrawler.toString())
                    .queryParam("lat", kakaoMapInfo.getLat())
                    .queryParam("lng", kakaoMapInfo.getLng())
                    .queryParam("level", kakaoMapInfo.getLevel())
                    .queryParam("type", kakaoMapInfo.getType())
                    .queryParam("layerMode", kakaoMapInfo.isLayerMode())
                    .build(true);

            driver = new CustomChromeDriver();
            WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(30));
            driver.get(uri.toString());
            waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("checker_true")));

            return Optional.ofNullable(driver.getFullScreenshot());

        } catch (Exception e){
            return Optional.empty();
        } finally {
            if(driver != null){
                driver.close();
                driver.quit();
            }

            available = true;
        }

    }


    public synchronized boolean isAvailable(){
        return this.available;
    }


}
