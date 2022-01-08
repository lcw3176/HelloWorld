package com.joebrooks.mapshotserver.domain.map.kakao.controller;

import com.joebrooks.mapshotserver.domain.map.kakao.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.map.kakao.service.ChromeDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.Queue;


@CrossOrigin("https://testservermapshot.netlify.app")
@RestController
@RequestMapping("/map/kakao")
@RequiredArgsConstructor
public class KakaoMapController {

    private final ChromeDriverService chromeDriverService;
    private final Queue<HttpSession> waitQueue = new LinkedList<>();


    @GetMapping
    public ResponseEntity getRequestAvailable(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        if(!waitQueue.contains(session)){
            waitQueue.add(session);
        }

        if(chromeDriverService.isAvailable() && waitQueue.peek().equals(session)){
            waitQueue.poll();

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

