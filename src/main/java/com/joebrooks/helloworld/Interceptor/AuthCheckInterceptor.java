package com.joebrooks.helloworld.Interceptor;

import com.joebrooks.helloworld.Common.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthCheckInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.cookie.name}")
    private String cookieName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{

        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(var i : cookies){
                if(i.getName().equals(cookieName)){
                    if(jwtTokenProvider.isValidate(i.getValue())){
                        return true;
                    } else{
                        break;
                    }

                }
            }

        }

        response.sendRedirect("/login");
        return false;
    }
}
