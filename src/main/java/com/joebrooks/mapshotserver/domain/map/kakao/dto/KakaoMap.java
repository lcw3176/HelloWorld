package com.joebrooks.mapshotserver.domain.map.kakao.dto;

import lombok.Data;

@Data
public class KakaoMap {
    private boolean layerMode;
    private double lat;
    private double lng;
    private int level;
    private String type;
}
