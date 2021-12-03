package com.joebrooks.mapshotserver.home.service;

import com.joebrooks.mapshotserver.home.datatype.RadiusType;
import com.joebrooks.mapshotserver.home.domain.Coor;
import org.springframework.stereotype.Service;

@Service
public class KakaoMapService implements IMapService {

    @Override
    public byte[] getImage(int type, RadiusType radiusType, Coor coor) {
        return new byte[0];
    }
}
