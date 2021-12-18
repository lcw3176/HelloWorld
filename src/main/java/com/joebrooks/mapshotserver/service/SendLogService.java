package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.domain.DailyInfo;
import com.joebrooks.mapshotserver.domain.OnFailed;
import com.joebrooks.mapshotserver.domain.OnSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class SendLogService {

    private final SlackService slackService;
    private final MakeMessageService makeMessageService;

    private void preCheck(int usingCount){
        if(usingCount == 1){
            DailyInfo.setUserCount(DailyInfo.getUserCount() + 1);
        }
    }

    public void success(OnSuccess onSuccess){
        preCheck(onSuccess.getUsingCount());

        DailyInfo.setSuccessCount(DailyInfo.getSuccessCount() + 1);

        String stringifyJsonMessage = makeMessageService.makeSuccessMessage(onSuccess);
        slackService.sendMessage(stringifyJsonMessage);
    }

    public void failed(OnFailed onFailed){
        preCheck(onFailed.getUsingCount());

        DailyInfo.setFailedCount(DailyInfo.getFailedCount() + 1);

        String stringifyJsonMessage = makeMessageService.makeFailedMessage(onFailed);
        slackService.sendMessage(stringifyJsonMessage);
    }

}
