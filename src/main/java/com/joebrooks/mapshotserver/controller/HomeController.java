package com.joebrooks.mapshotserver.controller;

import com.joebrooks.mapshotserver.dto.Map;
import com.joebrooks.mapshotserver.enumerate.MapProvider;
import com.joebrooks.mapshotserver.enumerate.UserRecvInfo;
import com.joebrooks.mapshotserver.service.map.IMapService;
import com.joebrooks.mapshotserver.service.map.KakaoMapService;
import com.joebrooks.mapshotserver.service.map.NaverMapService;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/")
@NoArgsConstructor
public class HomeController {

    private IMapService mapService;
    private KakaoMapService kakaoMapService;
    private NaverMapService naverMapService;

    @GetMapping
    public String home(){
        return "index";
    }

    @PostMapping
    public ResponseEntity getImage(@RequestBody HashMap<String, Object> userMapInfo){

        if(userMapInfo.get(UserRecvInfo.provider.toString()) == MapProvider.kakao){
            mapService = kakaoMapService;
            System.out.println("카카오");
        } else {
            mapService = naverMapService;
            System.out.println("네이버");
        }


//        byte[] binary = mapService.getImage(type, radius, coor);

//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(binary);
        return ResponseEntity.ok().body("헬로");
    }
}
