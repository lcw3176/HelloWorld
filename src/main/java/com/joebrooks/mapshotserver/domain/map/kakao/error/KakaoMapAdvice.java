package com.joebrooks.mapshotserver.domain.map.kakao.error;

import com.joebrooks.mapshotserver.global.dto.ErrorMessage;
import com.joebrooks.mapshotserver.infra.sns.IMessageClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.joebrooks.mapshotserver.domain.map.kakao")
@RequiredArgsConstructor
public class KakaoMapAdvice {

    private final IMessageClient iMessageClient;

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e) {
        int len = Math.min(ExceptionUtils.getStackTrace(e).length(), 1700);

        ErrorMessage errorMessage = ErrorMessage.builder()
                .name(e.getClass().toString())
                .message(ExceptionUtils.getStackTrace(e).substring(0, len))
                .build();

        iMessageClient.sendMessage(errorMessage);
    }
}
