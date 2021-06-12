package com.joebrooks.helloworld.Interceptor;

import com.joebrooks.helloworld.Common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthCheckInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.header}")
    private String headerName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{

        String token = request.getHeader(headerName);

        if(token == null || !jwtTokenProvider.isValidate(token)){
            response.sendRedirect("/error/noAuth");
            return false;
        }

        return true;
    }
}
