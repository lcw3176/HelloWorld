package com.joebrooks.mapshotserver.util;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeOptionUtil {

    private static ChromeOptions options = null;
    private static ChromeOptionUtil instance = null;

    public static ChromeOptionUtil getInstance(){
        if(instance == null){
            instance = new ChromeOptionUtil();
        }

        return instance;
    }

    public ChromeOptions getOptions(){
        if(options == null){
//            System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH"));
            options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.setBinary(System.getenv("GOOGLE_CHROME_BIN"));
        }
        return options;
    }
}
