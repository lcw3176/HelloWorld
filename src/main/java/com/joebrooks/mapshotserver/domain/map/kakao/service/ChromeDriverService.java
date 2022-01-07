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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChromeDriverService {

    private boolean available = true;
    private final QueryGenerator queryGenerator;
    private final long timeOutSeconds = 30;

    public Optional<byte[]> getImage(KakaoMap kakaoMapInfo) {
        CustomChromeDriver driver = null;

        try{
            available = false;

            UriComponents uri = UriComponentsBuilder.newInstance()
                    .scheme(UriInfo.scheme.toString())
                    .host(UriInfo.host.toString())
                    .path(UriInfo.kakaoMapCrawler.toString())
                    .queryParams(queryGenerator.getMaps(kakaoMapInfo))
                    .build(true);

            driver = new CustomChromeDriver();
            WebDriverWait waiter = new WebDriverWait(driver, timeOutSeconds);
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
