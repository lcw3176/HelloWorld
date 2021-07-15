package com.joebrooks.helloworld.Handler;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.joebrooks.helloworld.Common.ChatCommand;
import com.joebrooks.helloworld.Dto.User;
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
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class SocketHandler extends TextWebSocketHandler {
    private final HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
    private final JsonService jsonService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        echoMessage(message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String nickName = session.getAttributes().get("nickName").toString();
        sessionMap.put(nickName, session);

        TextMessage bornMessage = new TextMessage(jsonService.MakeJson(ChatCommand.userBorn, nickName, ""));
        echoMessage(bornMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getAttributes().get("nickName").toString());
        User.userList.remove(session.getAttributes().get("nickName").toString());
    }


    private void echoMessage(TextMessage message) throws IOException {
        Iterator<WebSocketSession> iter = sessionMap.values().iterator();

        while (iter.hasNext()) {
            WebSocketSession sess = iter.next();
            synchronized (sess) {
                if (sess.isOpen()) {
                    sess.sendMessage(message);
                }

            }
        }
    }
}

