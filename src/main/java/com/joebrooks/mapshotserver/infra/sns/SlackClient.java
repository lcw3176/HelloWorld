package com.joebrooks.mapshotserver.infra.sns;

import com.joebrooks.mapshotserver.domain.userLogging.dto.DailyInfo;
import com.joebrooks.mapshotserver.domain.userLogging.dto.OnFailed;
import com.joebrooks.mapshotserver.infra.sns.config.SlackConfig;
import com.joebrooks.mapshotserver.infra.sns.util.SlackMessageutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


@Component
@RequiredArgsConstructor
public class SlackClient {

    private final SlackMessageutil messageUtil;
    private final SlackConfig slackConfig;


    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void dailyReport(){
        int todayUser = DailyInfo.getUserCount();
        int todaySuccess = DailyInfo.getSuccessCount();
        int todayFailed = DailyInfo.getFailedCount();

        String stringifyJsonMessage = messageUtil.makeDailyMessage(todayUser, todaySuccess, todayFailed);

        sendMessage(stringifyJsonMessage);

        DailyInfo.initData();
    }

    public void sendFailedMessage(OnFailed failed){
        String stringifyJsonMessage = messageUtil.makeFailedMessage(failed);

        sendMessage(stringifyJsonMessage);
    }


    private void sendMessage(String message){
        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        rt.postForObject(slackConfig.getSlackUrl(), message, String.class);
    }

}
