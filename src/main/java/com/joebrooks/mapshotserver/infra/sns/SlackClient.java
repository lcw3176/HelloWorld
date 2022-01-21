package com.joebrooks.mapshotserver.infra.sns;

import com.joebrooks.mapshotserver.global.dto.ErrorMessage;
import com.joebrooks.mapshotserver.infra.sns.util.SlackMessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


@Component
@RequiredArgsConstructor
public class SlackClient implements IMessageClient{

    private final SlackMessageUtil slackMessageUtil;
    private final String slackUrl = System.getenv("SLACK_URL");

    @Override
    public void sendMessage(ErrorMessage errors) {
        RestTemplate rt = new RestTemplate();
        String message = slackMessageUtil.makeErrorMessage(errors);
        rt.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        rt.postForObject(slackUrl, message, String.class);
    }



}
