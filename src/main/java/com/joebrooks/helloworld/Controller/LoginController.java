package com.joebrooks.helloworld.Controller;

import com.joebrooks.helloworld.Common.JwtTokenProvider;
import com.joebrooks.helloworld.Dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    @Value("${jwt.header}")
    private String headerName;

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public String showForm(Model model, User user){
        return "index";
    }


    @PostMapping
    public String userLogin(HttpServletResponse res, Model model, User user){
        if(user.getNickName() == null || user.getNickName().equals("")){
            return "redirect:login";
        }
        String token = jwtTokenProvider.createToken(user.getNickName());

        System.out.println(token);
        Cookie jwtCookie = new Cookie(headerName, token);
        jwtCookie.setMaxAge(60*60*3);

        res.addCookie(jwtCookie);

        return "world";
    }

}
