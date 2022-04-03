package com.joebrooks.mapshotserver.kakaoMap;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("https://mapshot.netlify.app")
@RestController
@RequestMapping("/map/kakao")
@RequiredArgsConstructor
public class KakaoMapController {

    private final CaptureService captureService;

    @GetMapping
    public ResponseEntity<Boolean> getRequestAvailable() {

        if(captureService.isAvailable()){
            return ResponseEntity.ok().body(true);
        }

        return ResponseEntity.ok().body(false);
    }


    @PostMapping
    public ResponseEntity<byte[]> getFullSizeMapImage(@RequestBody KakaoMapRequest kakaoMapInfo) {

        byte[] data = captureService.getImage(kakaoMapInfo.getUrl());

        if(data != null){
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(data);
        }

        return ResponseEntity.badRequest()
                .build();
    }

}

