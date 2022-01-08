package com.joebrooks.mapshotserver.domain.map.kakao.service;

import com.joebrooks.mapshotserver.domain.map.kakao.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.map.kakao.util.CustomChromeDriver;
import com.joebrooks.mapshotserver.global.common.UriInfo;
import com.joebrooks.mapshotserver.global.util.QueryGenerator;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ChromeDriverService {

    private boolean available = true;
    private final QueryGenerator queryGenerator;
    private final long timeOutSeconds = 30;
    private CustomChromeDriver driver = null;
    private WebDriverWait waiter;

    public byte[] getImage(KakaoMap kakaoMapInfo) {

        try{
            available = false;

            UriComponents uri = UriComponentsBuilder.newInstance()
                    .scheme(UriInfo.scheme.toString())
                    .host(UriInfo.host.toString())
                    .path(UriInfo.kakaoMapCrawler.toString())
                    .queryParams(queryGenerator.getMaps(kakaoMapInfo))
                    .build(true);

            driver.get(uri.toString());
            waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("checker_true")));

            return driver.getFullScreenshot();

        } catch (Exception e){
            return null;
        } finally {
            if(driver != null){
                driver.quit();
            }

            available = true;
        }
    }

    public void init() throws Exception {
        driver = new CustomChromeDriver();
        waiter = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
    }


    public synchronized boolean isAvailable() {
        return this.available;
    }


}
