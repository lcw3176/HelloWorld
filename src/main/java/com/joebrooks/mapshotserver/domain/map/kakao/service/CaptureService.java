package com.joebrooks.mapshotserver.domain.map.kakao.service;

import com.joebrooks.mapshotserver.domain.map.kakao.customClass.CustomChromeDriver;
import com.joebrooks.mapshotserver.domain.map.kakao.dto.KakaoMap;
import com.joebrooks.mapshotserver.global.common.UriInfo;
import com.joebrooks.mapshotserver.global.util.QueryGenerator;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class CaptureService {

    private boolean available = true;

    private final CustomChromeDriver driver;
    private final WebDriverWait waiter;
    private final QueryGenerator queryGenerator;

    public byte[] getImage(KakaoMap kakaoMapInfo) {

        try{
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
            available = true;
        }
    }

    public synchronized boolean isAvailable() {
        if(available){
            available = false;

            return true;
        }

        return false;
    }


}
