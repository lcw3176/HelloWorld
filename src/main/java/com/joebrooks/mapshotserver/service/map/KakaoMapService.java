package com.joebrooks.mapshotserver.service.map;

import com.joebrooks.mapshotserver.dto.Coor;
import com.joebrooks.mapshotserver.enumerate.Radius;
import org.springframework.stereotype.Service;

@Service
public class KakaoMapService implements IMapService {

    @Override
    public byte[] getImage(int type, Radius radius, Coor coor) {
        return new byte[0];
    }
}
