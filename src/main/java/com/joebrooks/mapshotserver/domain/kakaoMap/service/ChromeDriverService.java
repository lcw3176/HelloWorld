package com.joebrooks.mapshotserver.domain.kakaoMap.service;

import com.joebrooks.mapshotserver.domain.kakaoMap.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.kakaoMap.util.CustomChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Service
public class ChromeDriverService {
    private boolean available = true;

    public byte[] getImage(KakaoMap kakaoMapInfo) throws Exception {
        available = false;

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("mapshot.herokuapp.com")
                .path("/proxy/kakao/crawl")
                .queryParam("lat", kakaoMapInfo.getLat())
                .queryParam("lng", kakaoMapInfo.getLng())
                .queryParam("level", kakaoMapInfo.getLevel())
                .queryParam("type", kakaoMapInfo.getType())
                .build(true);

        CustomChromeDriver driver = new CustomChromeDriver();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get(uri.toString());
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("checker_true")));

        byte[] srcFile = driver.getFullScreenshot();

        driver.close();
        driver.quit();

        available = true;

        return srcFile;
    }


    public synchronized boolean isAvailable(){
        return this.available;
    }


}
