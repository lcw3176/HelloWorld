package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.component.ChromeDriverEx;
import com.joebrooks.mapshotserver.domain.KakaoMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PreDestroy;
import java.time.Duration;

@Service
public class ChromeDriverService {

    private final WebDriverWait waiter;
    private final ChromeDriverEx driver;

    public ChromeDriverService(ChromeDriverEx driver) {
        this.driver = driver;
        this.waiter = new WebDriverWait(this.driver, Duration.ofSeconds(30));
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

        ((JavascriptExecutor) driver).executeScript("window.open()");
        driver.switchTo().window(driver.getWindowHandles().stream().findFirst().get());
        driver.get(uri.toString());
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("checker_true")));

        int requiredWidth = (Integer)driver.executeScript("return document.body.parentNode.scrollWidth");
        int requiredHeight = (Integer)driver.executeScript("return document.body.parentNode.scrollHeight");

        driver.manage().window().setSize(new Dimension(requiredWidth, requiredHeight));
        byte[] srcFile = driver.findElement(By.id("map")).getScreenshotAs(OutputType.BYTES);

        driver.close();
        driver.switchTo().window(driver.getWindowHandles().stream().findFirst().get());

        return srcFile;
    }

    @PreDestroy
    private void closeDriver(){
        this.driver.quit();
    }


}
