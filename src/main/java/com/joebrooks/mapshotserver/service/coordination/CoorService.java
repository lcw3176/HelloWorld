package com.joebrooks.mapshotserver.service.coordination;

import com.joebrooks.mapshotserver.dto.MapType;
import org.springframework.stereotype.Service;

@Service
public class CoorService {

    public CoorService(){
        MapType mapType = new MapType();
        mapType.setCompany(MapType.Company.Kakao);
        mapType.setType(MapType.Type.basic);
    }
}
