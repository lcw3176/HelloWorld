package com.joebrooks.mapshotserver.domain.kakaoMap.controller;

import com.joebrooks.mapshotserver.domain.kakaoMap.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.kakaoMap.service.ChromeDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("https://testservermapshot.netlify.app")
@RestController
@RequestMapping("/proxy/kakao")
@RequiredArgsConstructor
public class KakaoMapController {

    private final ChromeDriverService chromeDriverService;

    @GetMapping
    public ResponseEntity getRequestAvailable(){
        return ResponseEntity.ok().body(chromeDriverService.isAvailable());
    }

    @PostMapping
    public ResponseEntity requestMapImage(@RequestBody KakaoMap kakaoMapInfo) {

        byte[] srcFile = chromeDriverService.getImage(kakaoMapInfo);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(srcFile);
    }


}

