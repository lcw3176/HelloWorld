package com.joebrooks.mapshotserver.util;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.util.UriComponents;

import javax.annotation.PreDestroy;
import java.util.concurrent.Callable;


public class CallableChromeDriver implements Callable<byte[]> {

    private final WebDriverWait waiter;
    private final ChromeDriverEx driver;
    private String url;

    public CallableChromeDriver() throws Exception {
        this.driver = new ChromeDriverEx(ChromeOptionUtil.getInstance().getOptions());
        this.waiter = new WebDriverWait(this.driver, 30);
    }

    public void setKakaoMapInfo(UriComponents uriComponents){
        this.url = uriComponents.toString();
    }


    @Override
    public byte[] call() {
        driver.get(this.url);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("checker_true")));

        return driver.getFullScreenshotAs(OutputType.BYTES);
    }

    @PreDestroy
    public void closeDriver(){
        driver.quit();
    }
}
