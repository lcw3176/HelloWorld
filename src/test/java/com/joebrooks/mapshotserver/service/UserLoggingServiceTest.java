package com.joebrooks.mapshotserver.service;

import com.joebrooks.mapshotserver.domain.OnFailed;
import com.joebrooks.mapshotserver.domain.OnSuccess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserLoggingServiceTest {

    @Autowired
    private SendLogService userLoggingService;

    @Test
    public void successTest(){
        OnSuccess onSuccess = new OnSuccess();

        onSuccess.setUsedFunc("naver");
        onSuccess.setUsingCount(1);

        userLoggingService.success(onSuccess);
    }

    @Test
    public void failedTest(){
        OnFailed onFailed = new OnFailed();

        onFailed.setTitle("태공에러");
        onFailed.setMessage("태공이의 낚싯대가 부러짐");
        onFailed.setUsedFunc("naver");
        onFailed.setUsingCount(2);

        userLoggingService.failed(onFailed);
    }

}
