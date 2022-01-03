package com.joebrooks.mapshotserver.infra.sns.config;

import org.springframework.stereotype.Component;

@Component
public class SlackConfig {
    private final String url = System.getenv("SLACK_URL");

    public String getSlackUrl(){
        return this.url;
    }
}
