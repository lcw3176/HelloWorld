package com.joebrooks.mapshotserver.home.service;

import com.joebrooks.mapshotserver.home.dto.Coor;
import com.joebrooks.mapshotserver.home.datatype.RadiusType;

public interface IMapService {

    byte[] getImage(int type, RadiusType radiusType, Coor coor);

}
