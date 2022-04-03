package com.joebrooks.mapshotserver.webDriver;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaywrightConfig {

    @Bean(destroyMethod = "close")
    public Page chromePage(){
        BrowserType chromium = Playwright.create().chromium();

        return chromium.launch(new BrowserType.LaunchOptions()
                .setChannel("chrome"))
                .newContext()
                .newPage();
    }
}
