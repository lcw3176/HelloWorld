package com.joebrooks.mapshotserver.routine;

import com.joebrooks.mapshotserver.domain.DailyInfo;
import com.joebrooks.mapshotserver.service.MakeMessageService;
import com.joebrooks.mapshotserver.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyReport {

    private final MakeMessageService makeMessageService;
    private final SlackService slackService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void dailyReport(){
        int todayUser = DailyInfo.getUserCount();
        int todaySuccess = DailyInfo.getSuccessCount();
        int todayFailed = DailyInfo.getFailedCount();

        String stringifyJsonMessage = makeMessageService.makeDailyMessage(todayUser, todaySuccess, todayFailed);
        slackService.sendMessage(stringifyJsonMessage);
        DailyInfo.initData();
    }
}
