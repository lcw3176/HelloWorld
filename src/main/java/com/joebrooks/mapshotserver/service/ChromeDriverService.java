package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.domain.KakaoMap;
import com.joebrooks.mapshotserver.util.ChromeDriverEx;
import com.joebrooks.mapshotserver.util.ChromeOptionUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PreDestroy;
import java.util.ArrayList;

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

        ((JavascriptExecutor) driver).executeScript("window.open()");
        driver.get(uri.toString());
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("checker_true")));
        byte[] srcFile = driver.getFullScreenshot();
        driver.close();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        return srcFile;
    }

    @PreDestroy
    private void closeDriver(){
        this.driver.quit();
    }


}
