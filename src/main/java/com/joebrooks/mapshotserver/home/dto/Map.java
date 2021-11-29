package com.joebrooks.mapshotserver.home.dto;

import com.joebrooks.mapshotserver.enumerate.CommonMapType;
import com.joebrooks.mapshotserver.enumerate.MapProvider;
import com.joebrooks.mapshotserver.enumerate.Radius;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Map {
    private MapProvider provider;
    private CommonMapType mapType;
    private Radius radius;
    private Coor centerCoor;

}
