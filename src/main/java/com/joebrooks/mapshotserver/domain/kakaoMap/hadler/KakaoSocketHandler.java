package com.joebrooks.mapshotserver.domain.kakaoMap.hadler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joebrooks.mapshotserver.domain.kakaoMap.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.kakaoMap.service.ChromeDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@CrossOrigin("https://testservermapshot.netlify.app")
@Component
@RequiredArgsConstructor
public class KakaoSocketHandler extends TextWebSocketHandler {

    private final ChromeDriverService chromeDriverService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        KakaoMap kakaoMapInfo = mapper.readValue(message.getPayload(), KakaoMap.class);

        if(!chromeDriverService.isRunning()){
            byte[] src = chromeDriverService.getImage(kakaoMapInfo);
            session.sendMessage(new BinaryMessage(src));
            session.close();
        } else {
            session.sendMessage(new TextMessage("wait..."));
        }
    }


}
