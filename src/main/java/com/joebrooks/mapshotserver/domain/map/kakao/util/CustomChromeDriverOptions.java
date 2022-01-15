package com.joebrooks.mapshotserver.domain.map.kakao.util;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
public class CustomChromeDriverOptions {

    private final String chromeDriverPath = "CHROMEDRIVER_PATH";
    private final String chromeBinPath = "GOOGLE_CHROME_BIN";
    private ChromeOptions options;

    public CustomChromeDriverOptions(){
        System.setProperty("webdriver.chrome.driver", System.getenv(chromeDriverPath));
        options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.setBinary(System.getenv(chromeBinPath));
    }

    public ChromeOptions getOptions(){
        return this.options;
    }

}
