package com.joebrooks.mapshotserver.log.service;

import com.joebrooks.mapshotserver.log.domain.Success;
import com.joebrooks.mapshotserver.user.domain.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class MessageServiceTest {
    private MessageService messageService = new MessageService();

    @Test
    public void makeSuccessMessageTest(){
        User user = User.builder().isFirstVisit(true).usingCount(1).build();
        Success success = Success.builder().date(new Date()).user(user).build();
        String message = messageService.makeSuccessMessage(success);

        System.out.println(message);
    }

    @Test
    public void makeErrorMessage(){
        User user = User.builder().isFirstVisit(true).usingCount(1).build();
        Success success = Success.builder().date(new Date()).user(user).build();
        String message = messageService.makeSuccessMessage(success);

        System.out.println(message);
    }
}
