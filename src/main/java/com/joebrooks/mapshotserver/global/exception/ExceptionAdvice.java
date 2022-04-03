package com.joebrooks.mapshotserver.global.exception;

import com.joebrooks.mapshotserver.global.logging.IMessageClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {


    private final IMessageClient iMessageClient;

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e) {
        int len = Math.min(ExceptionUtils.getStackTrace(e).length(), 1700);

        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .name(e.getClass().toString())
                .message(ExceptionUtils.getStackTrace(e).substring(0, len))
                .build();

        iMessageClient.sendMessage(errorMessage);
    }

//    private void sendErrorMessage(ErrorType error){
//        ExceptionMessage errorMessage = ExceptionMessage.builder()
//                .name(error.name())
//                .message(error.getDescription())
//                .build();
//
//        iMessageClient.sendMessage(errorMessage);
//    }
}
