package com.joebrooks.mapshotserver.domain;

import lombok.Data;

@Data
public class KakaoMap {
    private float lat;
    private float lng;
    private String level;
    private String type;
}
