package com.joebrooks.mapshotserver.domain.map.kakao.config;

import com.joebrooks.mapshotserver.domain.map.kakao.customClass.CustomChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ChromeDriverConfig {

    @Bean
    public ChromeOptions chromeOptions(){
        String chromeDriverPath = "CHROMEDRIVER_PATH";
        String chromeBinPath = "GOOGLE_CHROME_BIN";

        System.setProperty("webdriver.chrome.driver", System.getenv(chromeDriverPath));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.setBinary(System.getenv(chromeBinPath));

        return options;
    }

    @Bean(destroyMethod = "quit")
    public CustomChromeDriver customChromeDriver() throws Exception {
        return new CustomChromeDriver(chromeOptions());
    }

    @Bean
    public WebDriverWait webDriverWait() throws Exception {
        long timeOutSeconds = 30;

        return new WebDriverWait(customChromeDriver(), Duration.ofSeconds(timeOutSeconds));
    }
}
