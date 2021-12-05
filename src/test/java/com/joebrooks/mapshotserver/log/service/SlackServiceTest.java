package com.joebrooks.mapshotserver.log.service;

import com.joebrooks.mapshotserver.log.domain.Failed;
import com.joebrooks.mapshotserver.log.domain.Success;
import com.joebrooks.mapshotserver.user.UserController;
import com.joebrooks.mapshotserver.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class SlackServiceTest {

    @Autowired
    private SlackService slackService;

    @Autowired
    private MessageService messageService;

    @Test
    public void sendMessageTest(){
        slackService.sendMesseage(getSuccessMessage());
        slackService.sendMesseage(getFailedMessage());
    }


    private String getSuccessMessage(){
        User user = User.builder().isFirstVisit(true).usingCount(1).build();
        Success success = Success.builder().date(new Date()).user(user).build();
        return messageService.makeSuccessMessage(success);
    }

    private String getFailedMessage(){
        Failed failed = Failed.builder().date(new Date()).title("Runtime Exception").message("bla bla...").build();
        return messageService.makeFailedMessage(failed);
    }

}
