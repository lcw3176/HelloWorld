package com.joebrooks.helloworld.Handler;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.joebrooks.helloworld.Common.ChatCommand;
import com.joebrooks.helloworld.Service.JsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class SocketHandler extends TextWebSocketHandler {
    private final HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
    private final JsonService jsonService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

        for(var i : sessionMap.values()){
            if(i != null && i.isOpen()){
                synchronized (i){
                    i.sendMessage(message);
                }
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String nickName = session.getAttributes().get("nickName").toString();
        
        // 현재 접속자 리스폰 시키기
        String messageToMe = jsonService.MakeJson(ChatCommand.IAmBorn, nickName, "");
        session.sendMessage(new TextMessage(messageToMe));

        // 기존 접속자들에게 현재 접속자 존재 알림
        String alertMeToOthers = jsonService.MakeJson(ChatCommand.YouAreBorn, nickName, "");

        for(var i : sessionMap.values()){
            i.sendMessage(new TextMessage(alertMeToOthers));
        }


        // 현재 접속자에게 기존 접속자들 정보 주기
//        for(var i : sessionMap.keySet()){
//            String peopleInAlready = jsonService.MakeJson(ChatCommand.YouAreBorn, i, "");
//
//            session.sendMessage(new TextMessage(peopleInAlready));
//        }


        sessionMap.put(nickName, session);


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getAttributes().get("nickName").toString());
    }
}

