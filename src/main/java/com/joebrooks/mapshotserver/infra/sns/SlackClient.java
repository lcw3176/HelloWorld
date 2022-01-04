package com.joebrooks.mapshotserver.infra.sns;

import com.joebrooks.mapshotserver.infra.sns.config.SlackConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


@Component
@RequiredArgsConstructor
@Primary
public class SlackClient implements ISnsClient {

    private final SlackConfig slackConfig;

    @Override
    public void sendMessage(String message) {
        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        rt.postForObject(slackConfig.getSlackUrl(), message, String.class);
    }
}
