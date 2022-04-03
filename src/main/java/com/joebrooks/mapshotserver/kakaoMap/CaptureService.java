package com.joebrooks.mapshotserver.kakaoMap;

import com.joebrooks.mapshotserver.webDriver.CustomChromeDriver;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaptureService {

    private boolean available = true;
    private final CustomChromeDriver driver;
    private final WebDriverWait waiter;

    public byte[] getImage(String url) {

        try{
            driver.get(url);
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
