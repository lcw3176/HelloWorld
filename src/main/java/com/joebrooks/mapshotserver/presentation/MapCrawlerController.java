package com.joebrooks.mapshotserver.presentation;

import com.joebrooks.mapshotserver.kakaoMap.KakaoMapRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/map/kakao/crawl")
public class MapCrawlerController {


    @GetMapping
    public String getKakaoMap(@ModelAttribute KakaoMapRequest kakaoMap, Model model){
        model.addAttribute("kakaoMapInfo", kakaoMap);

        return "map/kakao";
    }
}
