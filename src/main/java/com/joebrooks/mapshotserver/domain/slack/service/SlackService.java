package com.joebrooks.mapshotserver.domain.slack.service;

import com.joebrooks.mapshotserver.domain.slack.dto.DailyInfo;
import com.joebrooks.mapshotserver.domain.slack.dto.OnFailed;
import com.joebrooks.mapshotserver.domain.slack.dto.OnSuccess;
import com.joebrooks.mapshotserver.domain.slack.util.MessageUtil;
import com.joebrooks.mapshotserver.infra.config.SlackConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SlackService {

    private final SlackConfig slackConfig;
    private final MessageUtil messageUtil;

    public void onSuccess(OnSuccess onSuccess){
        preCheck(onSuccess.getUsingCount());

        DailyInfo.setSuccessCount(DailyInfo.getSuccessCount() + 1);

        String stringifyJsonMessage = messageUtil.makeSuccessMessage(onSuccess);
        sendMessage(stringifyJsonMessage);
    }

    public void onFailed(OnFailed onFailed){
        preCheck(onFailed.getUsingCount());

        DailyInfo.setFailedCount(DailyInfo.getFailedCount() + 1);

        String stringifyJsonMessage = messageUtil.makeFailedMessage(onFailed);
        sendMessage(stringifyJsonMessage);
    }


    public void onDaliy(){
        int todayUser = DailyInfo.getUserCount();
        int todaySuccess = DailyInfo.getSuccessCount();
        int todayFailed = DailyInfo.getFailedCount();

        String stringifyJsonMessage = messageUtil.makeDailyMessage(todayUser, todaySuccess, todayFailed);

        sendMessage(stringifyJsonMessage);
    }

    private void sendMessage(String message){
        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        rt.postForObject(slackConfig.getSlackUrl(), message, String.class);
    }


    private void preCheck(int usingCount){
        if(usingCount == 1){
            DailyInfo.setUserCount(DailyInfo.getUserCount() + 1);
        }
    }


}
