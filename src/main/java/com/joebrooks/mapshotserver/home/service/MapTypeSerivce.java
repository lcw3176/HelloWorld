package com.joebrooks.mapshotserver.service.map;

import com.joebrooks.mapshotserver.enumerate.CommonMapType;
import com.joebrooks.mapshotserver.enumerate.KakaoMapType;
import com.joebrooks.mapshotserver.enumerate.MapProvider;
import com.joebrooks.mapshotserver.enumerate.NaverMapType;
import org.springframework.stereotype.Service;

@Service
public class MapTypeSerivce {

    public String getTypeByProvider(MapProvider provider, CommonMapType type){
        int index = CommonMapType.valueOf(type.toString()).ordinal();

        if(provider == MapProvider.naver){
            return NaverMapType.values()[index].toString();
        } else {
            return KakaoMapType.values()[index].toString();
        }
    }


}
