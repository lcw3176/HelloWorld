package com.joebrooks.helloworld.Interceptor;

import com.joebrooks.helloworld.Common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Value("${jwt.cookie.name}")
    private String cookieName;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        ServletServerHttpRequest servletServerRequest = (ServletServerHttpRequest) request;
        HttpServletRequest servletRequest = servletServerRequest.getServletRequest();
        Cookie token = WebUtils.getCookie(servletRequest, cookieName);

        if(token != null && !token.equals("")){
            attributes.put("nickName",jwtTokenProvider.getNicknameFromClaims(token.getValue()));
        }

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

}
