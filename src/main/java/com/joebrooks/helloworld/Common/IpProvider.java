package com.joebrooks.helloworld.Common;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class IpProvider {

    public String getIp(HttpServletRequest req){
        String ip = req.getHeader("X-FORWARDED-FOR");

        if (ip == null){
            ip = req.getRemoteAddr();
        }

        return ip;
    }
}
