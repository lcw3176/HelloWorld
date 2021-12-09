package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.domain.OnFailed;
import com.joebrooks.mapshotserver.domain.OnSuccess;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    private void preCheck(boolean isFirst){
        if(isFirst){
            userCount.set(userCount.get() + 1);
        }
    }

    public void success(OnSuccess onSuccess){
        preCheck(onSuccess.isFirstVisit());

        successCount.set(successCount.get() + 1);
        onSuccess.setDate(new Date());

        String stringifyJsonMessage = jsonMessageService.makeSuccessMessage(onSuccess);
        slackService.sendMesseage(stringifyJsonMessage);
    }

    public void failed(OnFailed onFailed){
        preCheck(onFailed.isFirstVisit());

        failedCount.set(failedCount.get() + 1);
        onFailed.setDate(new Date());

        String stringifyJsonMessage = jsonMessageService.makeFailedMessage(onFailed);
        slackService.sendMesseage(stringifyJsonMessage);
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void dailyReport(){
        int todayUser = userCount.get();
        int todaySuccess = successCount.get();
        int todayFailed = failedCount.get();

        String stringifyJsonMessage = jsonMessageService.makeDaliyMessage(todayUser, todaySuccess, todayFailed);
        slackService.sendMesseage(stringifyJsonMessage);

        userCount.set(0);
        successCount.set(0);
        failedCount.set(0);
    }
}
