package com.joebrooks.mapshotserver.home.service;

import com.joebrooks.mapshotserver.home.datatype.RadiusType;
import com.joebrooks.mapshotserver.home.domain.Coor;
import com.joebrooks.mapshotserver.home.datatype.CommonMapType;
import com.joebrooks.mapshotserver.home.datatype.MapProviderType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class NaverMapService implements IMapService {

    private MapTypeSerivce mapTypeSerivce;


    @Override
    public byte[] getImage(int type, RadiusType radiusType, Coor coor){
        String mapType = mapTypeSerivce.getTypeByProvider(MapProviderType.naver, CommonMapType.values()[type]);
//        map.setKey("네이버키값");


        return null;
    }

    private Coor getFirstBlock(Coor centerCoor){
        return null;
    }

    private Coor getNextCoor(){
        return null;
    }
}
