package com.joebrooks.mapshotserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coor {
    private float lat;
    private float lng;

    public float getX(){
        return Float.min(lat, lng);
    }

    public float getY(){
        return Float.max(lat, lng);
    }
}
