package com.joebrooks.mapshotserver.domain.userLogging.service;

import com.joebrooks.mapshotserver.domain.userLogging.dto.DailyInfo;
import com.joebrooks.mapshotserver.domain.userLogging.dto.OnFailed;
import com.joebrooks.mapshotserver.domain.userLogging.dto.OnSuccess;
import com.joebrooks.mapshotserver.infra.sns.SlackClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final SlackClient slackClient;

    public void onSuccess(OnSuccess onSuccess){
        preCheck(onSuccess.getUsingCount());

        DailyInfo.setSuccessCount(DailyInfo.getSuccessCount() + 1);
    }

    public void onFailed(OnFailed onFailed){
        preCheck(onFailed.getUsingCount());

        DailyInfo.setFailedCount(DailyInfo.getFailedCount() + 1);
        slackClient.sendFailedMessage(onFailed);
    }


    private void preCheck(int usingCount){
        if(usingCount == 1){
            DailyInfo.setUserCount(DailyInfo.getUserCount() + 1);
        }
    }

}
