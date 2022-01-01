package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.component.ChromeDriverEx;
import com.joebrooks.mapshotserver.domain.KakaoMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Service
public class ChromeDriverService {


    public synchronized byte[] getImage(KakaoMap kakaoMapInfo) throws Exception {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("mapshot.herokuapp.com")
                .path("/proxy/kakao/crawl")
                .queryParam("lat", kakaoMapInfo.getLat())
                .queryParam("lng", kakaoMapInfo.getLng())
                .queryParam("level", kakaoMapInfo.getLevel())
                .queryParam("type", kakaoMapInfo.getType())
                .build(true);

        ChromeDriverEx driver = new ChromeDriverEx();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get(uri.toString());
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("checker_true")));

        Integer requiredWidth = ((Long)driver.executeScript("return document.body.parentNode.scrollWidth")).intValue();
        Integer requiredHeight = ((Long)driver.executeScript("return document.body.parentNode.scrollHeight")).intValue();

        driver.manage().window().setSize(new Dimension(requiredWidth, requiredHeight));
        byte[] srcFile = driver.findElement(By.id("map")).getScreenshotAs(OutputType.BYTES);

        driver.close();
        driver.quit();

        return srcFile;
    }


}
