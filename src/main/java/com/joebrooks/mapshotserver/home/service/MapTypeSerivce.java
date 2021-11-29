package com.joebrooks.mapshotserver.home.service;

import com.joebrooks.mapshotserver.home.datatype.CommonMapType;
import com.joebrooks.mapshotserver.home.datatype.KakaoMapType;
import com.joebrooks.mapshotserver.home.datatype.MapProviderType;
import com.joebrooks.mapshotserver.home.datatype.NaverMapType;
import org.springframework.stereotype.Service;

@Service
public class MapTypeSerivce {

    public String getTypeByProvider(MapProviderType provider, CommonMapType type){
        int index = CommonMapType.valueOf(type.toString()).ordinal();

        if(provider == MapProviderType.naver){
            return NaverMapType.values()[index].toString();
        } else {
            return KakaoMapType.values()[index].toString();
        }
    }


}
