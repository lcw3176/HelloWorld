package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.domain.KakaoMap;
import com.joebrooks.mapshotserver.util.ChromeDriverEx;
import com.joebrooks.mapshotserver.util.ChromeOptionUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PreDestroy;

@Service
public class ChromeDriverService {

    private WebDriverWait waiter;
    private ChromeDriverEx driver;

    public ChromeDriverService() throws Exception {
        this.driver = new ChromeDriverEx(ChromeOptionUtil.getInstance().getOptions());
        this.waiter = new WebDriverWait(this.driver, 30);
    }

    public synchronized byte[] getImage(KakaoMap kakaoMapInfo) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("mapshot.herokuapp.com")
                .path("/proxy/kakao/crawl")
                .queryParam("lat", kakaoMapInfo.getLat())
                .queryParam("lng", kakaoMapInfo.getLng())
                .queryParam("level", kakaoMapInfo.getLevel())
                .queryParam("type", kakaoMapInfo.getType())
                .build(true);

        driver.get(uri.toString());
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("checker_true")));

        return driver.getFullScreenshot();
    }

    @PreDestroy
    private void closeDriver(){
        this.driver.quit();
    }


}
