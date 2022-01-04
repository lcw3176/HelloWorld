package com.joebrooks.mapshotserver.domain.map.kakao.controller;

import com.joebrooks.mapshotserver.domain.map.kakao.dto.KakaoMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/map/kakao/crawl")
public class MapCrawlerController {


    @GetMapping
    public String getKakaoMap(@ModelAttribute KakaoMap kakaoMap, Model model){
        model.addAttribute("kakaoMapInfo", kakaoMap);

        return "map/kakao";
    }
}
