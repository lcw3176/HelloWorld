package com.joebrooks.mapshotserver.domain.map.kakao.controller;

import com.joebrooks.mapshotserver.domain.map.kakao.dto.KakaoMap;
import com.joebrooks.mapshotserver.domain.map.kakao.service.CaptureService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
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
    public ResponseEntity getRequestAvailable() {

        if(captureService.isAvailable()){
            return ResponseEntity.ok().body(true);
        }

        return ResponseEntity.ok().body(false);
    }

    @PostMapping
    public ResponseEntity<ByteArrayResource> getFullSizeMapImage(@RequestBody KakaoMap kakaoMapInfo) {

        byte[] srcFile = captureService.getImage(kakaoMapInfo.getUrl());

        if(srcFile != null){
            ByteArrayResource byteArr = new ByteArrayResource(srcFile);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentLength(byteArr.contentLength())
                    .body(byteArr);
        }

        return ResponseEntity.badRequest().build();
    }

}

