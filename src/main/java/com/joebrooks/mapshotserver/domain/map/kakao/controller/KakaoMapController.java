package com.joebrooks.mapshotserver.domain.map.kakao.controller;

import com.joebrooks.mapshotserver.domain.map.kakao.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.map.kakao.service.ChromeDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin("https://testservermapshot.netlify.app")
@RestController
@RequestMapping("/map/kakao")
@RequiredArgsConstructor
public class KakaoMapController {

    private final ChromeDriverService chromeDriverService;

    @GetMapping
    public ResponseEntity getRequestAvailable(){
        return ResponseEntity.ok().body(chromeDriverService.isAvailable());
    }

    @PostMapping
    public ResponseEntity requestMapImage(@RequestBody KakaoMap kakaoMapInfo) {

        Optional<byte[]> srcFile = chromeDriverService.getImage(kakaoMapInfo);

        if(srcFile.isPresent()){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(srcFile);
        }

        return ResponseEntity.badRequest().build();
    }

}

