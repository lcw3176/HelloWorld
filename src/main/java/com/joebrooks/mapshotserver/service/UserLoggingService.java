package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.domain.OnFailed;
import com.joebrooks.mapshotserver.domain.OnSuccess;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserLoggingService {

    private static AtomicInteger userCount = new AtomicInteger(0);
    private static AtomicInteger successCount = new AtomicInteger(0);
    private static AtomicInteger failedCount = new AtomicInteger(0);

    private SlackService slackService;
    private JsonMessageService jsonMessageService;

    public UserLoggingService(SlackService slackService, JsonMessageService jsonMessageService){
        this.slackService = slackService;
        this.jsonMessageService = jsonMessageService;
    }

    private void preCheck(int usingCount){
        if(usingCount == 1){
            userCount.set(userCount.get() + 1);
        }
    }

    public void success(OnSuccess onSuccess){
        preCheck(onSuccess.getUsingCount());

        successCount.set(successCount.get() + 1);

        String stringifyJsonMessage = jsonMessageService.makeSuccessMessage(onSuccess);
        slackService.sendMessage(stringifyJsonMessage);
    }

    public void failed(OnFailed onFailed){
        preCheck(onFailed.getUsingCount());

        failedCount.set(failedCount.get() + 1);

        String stringifyJsonMessage = jsonMessageService.makeFailedMessage(onFailed);
        slackService.sendMessage(stringifyJsonMessage);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void dailyReport(){
        int todayUser = userCount.get();
        int todaySuccess = successCount.get();
        int todayFailed = failedCount.get();

        String stringifyJsonMessage = jsonMessageService.makeDailyMessage(todayUser, todaySuccess, todayFailed);
        slackService.sendMessage(stringifyJsonMessage);

        userCount.set(0);
        successCount.set(0);
        failedCount.set(0);
    }
}
