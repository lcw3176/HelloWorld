package com.joebrooks.mapshotserver.domain.map.kakao.controller;

import com.joebrooks.mapshotserver.domain.map.kakao.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.map.kakao.service.ChromeDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.Queue;


@CrossOrigin("https://testservermapshot.netlify.app")
@RestController
@RequestMapping("/map/kakao")
@RequiredArgsConstructor
public class KakaoMapController {

    private final ChromeDriverService chromeDriverService;
    private final Queue<String> waitQueue = new LinkedList<>();
    private final String waitCookieName = "mapshotTurn";

    @GetMapping
    public ResponseEntity getRequestAvailable(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userValue = null;
        Cookie userCookie = null;

        for(Cookie i : request.getCookies()){
            if(i.getName().equals(waitCookieName)){
                userValue = i.getValue();
                userCookie = i;
                break;
            }
        }

        if(userValue == null){
            userValue = Long.toString(System.currentTimeMillis());
            userCookie = new Cookie(waitCookieName, userValue);
            response.addCookie(userCookie);

            waitQueue.add(userValue);
        }

        if(chromeDriverService.isAvailable() && waitQueue.peek().equals(userValue)){
            waitQueue.poll();
            userCookie.setMaxAge(0);

            response.addCookie(userCookie);

            return ResponseEntity.ok().body(true);
        }

        return ResponseEntity.ok().body(waitQueue.size());
    }

    @PostMapping
    public ResponseEntity requestMapImage(@RequestBody KakaoMap kakaoMapInfo) {

        byte[] srcFile = chromeDriverService.getImage(kakaoMapInfo);

        if(srcFile != null){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(srcFile);
        }

        return ResponseEntity.badRequest().build();
    }

}

