package com.joebrooks.helloworld.Controller;

import com.joebrooks.helloworld.Common.IpProvider;
import com.joebrooks.helloworld.Common.JwtTokenProvider;
import com.joebrooks.helloworld.Dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth/world")
public class WorldController {

    @Value("${jwt.cookie.name}")
    private String cookieName;

    private final JwtTokenProvider jwtTokenProvider;
    private final IpProvider ipProvider;

    @GetMapping
    public String showForm(HttpServletRequest req, HttpServletResponse res){
        Cookie cookie = WebUtils.getCookie(req, cookieName);

        if(cookie != null){
            String nickName = jwtTokenProvider.getNicknameFromClaims(cookie.getValue());

            if(!User.userList.containsKey(nickName)){
                User user = new User();
                user.setNickName(nickName);
                user.setIp(ipProvider.getIp(req));

                User.userList.put(nickName, user);

                return "world";
            }
        }

        return "redirect:/login";
    }
}
