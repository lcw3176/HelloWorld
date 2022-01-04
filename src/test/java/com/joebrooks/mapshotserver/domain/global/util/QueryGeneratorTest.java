package com.joebrooks.mapshotserver.domain.global.util;

import com.joebrooks.mapshotserver.domain.map.kakao.dto.KakaoMap;
import com.joebrooks.mapshotserver.global.common.UriInfo;
import com.joebrooks.mapshotserver.global.util.QueryGenerator;
import org.junit.Test;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class QueryGeneratorTest {

    @Test
    public void parseTest() {
        QueryGenerator queryGenerator = new QueryGenerator();

        KakaoMap kakaoMap = new KakaoMap();
        kakaoMap.setLat(37.1111);
        kakaoMap.setLng(142.1345);
        kakaoMap.setLayerMode(true);
        kakaoMap.setLevel(1);
        kakaoMap.setType("satle");

        MultiValueMap<String, String> map = queryGenerator.getMaps(kakaoMap);

        for(String i : map.keySet()){
            System.out.println(i + " " + map.get(i));
        }
    }

    @Test
    public void uriTest(){
        QueryGenerator queryGenerator = new QueryGenerator();

        KakaoMap kakaoMap = new KakaoMap();
        kakaoMap.setLat(37.1111);
        kakaoMap.setLng(142.1345);
        kakaoMap.setLayerMode(true);
        kakaoMap.setLevel(1);
        kakaoMap.setType("satle");

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme(UriInfo.scheme.toString())
                .host(UriInfo.host.toString())
                .path(UriInfo.kakaoMapCrawler.toString())
                .queryParams(queryGenerator.getMaps(kakaoMap))
                .build(true);

        System.out.println(uri.toString());
    }
}
