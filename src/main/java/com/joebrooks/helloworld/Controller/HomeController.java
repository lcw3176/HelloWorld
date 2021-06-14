package com.joebrooks.helloworld.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    @Value("${jwt.cookie.name}")
    private String cookieName;

    @GetMapping
    public String checkUserStatus(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();

        if(cookies != null){
            for(var i : cookies){
                if(i.getName().equals(cookieName)){
                    return "redirect:/auth/world";
                }
            }
        }


        return "redirect:/login";
    }
}
