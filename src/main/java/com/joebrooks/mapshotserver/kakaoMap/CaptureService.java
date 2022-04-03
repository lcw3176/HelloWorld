package com.joebrooks.mapshotserver.kakaoMap;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotType;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaptureService {

    private boolean available = true;
    private final Page page;

    public byte[] getImage(String url) {

        try{
            page.navigate(url);
            page.waitForSelector("#checker_true", new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.ATTACHED));

            return page.screenshot(new Page.ScreenshotOptions()
                    .setType(ScreenshotType.JPEG)
                    .setFullPage(true)
                    .setQuality(100));

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
