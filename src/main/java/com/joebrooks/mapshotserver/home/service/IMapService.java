package com.joebrooks.mapshotserver.service.map;

import com.joebrooks.mapshotserver.dto.Coor;
import com.joebrooks.mapshotserver.enumerate.Radius;

public interface IMapService {

    byte[] getImage(int type, Radius radius, Coor coor);

}
