package com.joebrooks.mapshotserver.home;

import com.joebrooks.mapshotserver.home.dto.Coor;
import com.joebrooks.mapshotserver.home.datatype.MapProviderType;
import com.joebrooks.mapshotserver.home.datatype.RadiusType;
import com.joebrooks.mapshotserver.home.datatype.UserConfigType;
import com.joebrooks.mapshotserver.home.dto.UserConfig;
import com.joebrooks.mapshotserver.home.service.IMapService;
import com.joebrooks.mapshotserver.home.service.KakaoMapService;
import com.joebrooks.mapshotserver.home.service.NaverMapService;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity getImage(@RequestBody @Validated UserConfig userConfig, Errors error){

        if(error.hasErrors()){
            System.out.println(error.getFieldError());
            return ResponseEntity.badRequest().build();
        }
//
//        if(userConfig.get(UserConfigType.provider.toString()) == MapProviderType.kakao){
//            mapService = kakaoMapService;
//        } else {
//            mapService = naverMapService;
//        }
//
//        float lat = Float.parseFloat(userConfig.get(UserConfigType.lat.toString()).toString());
//        float lng = Float.parseFloat(userConfig.get(UserConfigType.lng.toString()).toString());
//        int type = Integer.parseInt(userConfig.get(UserConfigType.mapType.toString()).toString());
//        RadiusType radiusType = RadiusType.valueOf(userConfig.get(UserConfigType.radius.toString()).toString());
//
//        Coor centerCoor = Coor
//                .builder()
//                .lat(lat)
//                .lng(lng).build();
//
//        byte[] binary = mapService.getImage(type, radiusType, centerCoor);

//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(binary);
        return ResponseEntity.ok().body("헬로");
    }
}
