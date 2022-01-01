package com.joebrooks.mapshotserver.global.routine;

import com.joebrooks.mapshotserver.domain.slack.dto.DailyInfo;
import com.joebrooks.mapshotserver.domain.slack.service.SlackService;
import com.joebrooks.mapshotserver.domain.slack.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyReport {

    private final MessageUtil messageUtil;
    private final SlackService slackService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void dailyReport(){
        slackService.onDaliy();
        DailyInfo.initData();
    }
}
