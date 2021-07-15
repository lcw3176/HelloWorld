package com.joebrooks.helloworld.Controller;

import com.joebrooks.helloworld.Common.IpProvider;
import com.joebrooks.helloworld.Common.JwtTokenProvider;
import com.joebrooks.helloworld.Dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    @Value("${jwt.cookie.name}")
    private String cookieName;

    private final JwtTokenProvider jwtTokenProvider;
    private final IpProvider ipProvider;

    @GetMapping
    public String showForm(User user){
        return "login";
    }


    @PostMapping
    public String userLogin(HttpServletRequest req, HttpServletResponse res, Model model, User user){

        String ip = ipProvider.getIp(req);

        if(User.userList.values().stream().anyMatch(i -> i.getIp().equals(ip))){
            model.addAttribute("error", "existIp");
            return "login";
        }

        if(User.userList.keySet().contains(user.getNickName())){
            model.addAttribute("error", "existNickname");
            return "login";
        }

        if(user.getNickName() == null || user.getNickName().equals("")){
            model.addAttribute("error", "empty");
            return "login";
        }

        Cookie jwtCookie = new Cookie(cookieName, jwtTokenProvider.createToken(user.getNickName()));
        jwtCookie.setMaxAge(60*60*3);
        res.addCookie(jwtCookie);

        user.setIp(ip);
        User.userList.put(user.getNickName(), user);

        return "world";
    }

}
