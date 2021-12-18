package com.joebrooks.mapshotserver.component;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeOptionEx {

    public ChromeOptions getOptions(){

            System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
//            System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH"));
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-gpu");
//            options.addArguments("--disable-dev-shm-usage");
//            options.setBinary(System.getenv("GOOGLE_CHROME_BIN"));

        return options;
    }
}
