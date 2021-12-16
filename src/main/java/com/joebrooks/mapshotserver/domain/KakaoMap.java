package com.joebrooks.mapshotserver.domain;

import lombok.Data;

@Data
public class KakaoMap {
    private double lat;
    private double lng;
    private String level;
    private String type;
}
