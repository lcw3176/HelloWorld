package com.joebrooks.mapshotserver.domain.map.kakao.error;

import com.joebrooks.mapshotserver.domain.map.kakao.controller.KakaoMapController;
import com.joebrooks.mapshotserver.domain.map.kakao.controller.MapCrawlerController;
import com.joebrooks.mapshotserver.global.dto.ErrorMessage;
import com.joebrooks.mapshotserver.infra.sns.IMessageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

@RestControllerAdvice("com.joebrooks.mapshotserver.domain.map.kakao")
@RequiredArgsConstructor
public class KakaoMapAdvice {

    private final IMessageClient iMessageClient;
    
    // 슬랙 메세지 포맷 수정 필요
//    @ExceptionHandler(Exception.class)
//    public void exceptionHandler(Exception e) throws IOException {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        PrintStream stream = new PrintStream(out);
//        e.printStackTrace(stream);
//
//        ErrorMessage errorMessage = ErrorMessage.builder()
//                .name(e.getClass().toString())
//                .message(out.toString())
//                .build();
//
//        iMessageClient.sendMessage(errorMessage);
//
//        stream.close();
//        out.close();
//
//    }
}
