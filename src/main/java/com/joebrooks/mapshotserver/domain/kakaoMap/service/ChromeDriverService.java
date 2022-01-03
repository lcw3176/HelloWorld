package com.joebrooks.mapshotserver.domain.kakaoMap.service;

import com.joebrooks.mapshotserver.domain.kakaoMap.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.kakaoMap.util.CustomChromeDriver;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ChromeDriverService {
    private boolean available = true;
    private final CustomChromeDriver driver;
    private WebDriverWait waiter;

    @PostConstruct
    public void init(){
        waiter = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public byte[] getImage(KakaoMap kakaoMapInfo) {

        try{
            available = false;

            UriComponents uri = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("mapshot.herokuapp.com")
                    .path("/proxy/kakao/crawl")
                    .queryParam("lat", kakaoMapInfo.getLat())
                    .queryParam("lng", kakaoMapInfo.getLng())
                    .queryParam("level", kakaoMapInfo.getLevel())
                    .queryParam("type", kakaoMapInfo.getType())
                    .queryParam("layerMode", kakaoMapInfo.isLayerMode())
                    .build(true);


            driver.get(uri.toString());
            waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("checker_true")));

            return driver.getFullScreenshot();

        } catch (Exception e){
            return null;
        } finally {
            available = true;
        }

    }

    @PreDestroy
    public void dispose(){
        driver.close();
        driver.quit();
    }

    public synchronized boolean isAvailable(){
        return this.available;
    }


}
