package com.joebrooks.mapshotserver.service.map;

import com.joebrooks.mapshotserver.dto.Coor;
import com.joebrooks.mapshotserver.enumerate.CommonMapType;
import com.joebrooks.mapshotserver.enumerate.MapProvider;
import com.joebrooks.mapshotserver.enumerate.Radius;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class NaverMapService implements IMapService {

    private MapTypeSerivce mapTypeSerivce;


    @Override
    public byte[] getImage(int type, Radius radius, Coor coor){
        String mapType = mapTypeSerivce.getTypeByProvider(MapProvider.naver, CommonMapType.values()[type]);
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
