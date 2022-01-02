package com.joebrooks.mapshotserver.domain.kakaoMap.dto;

import lombok.Data;

@Data
public class KakaoMap {
    private String layerMode;
    private double lat;
    private double lng;
    private String level;
    private String type;
}
