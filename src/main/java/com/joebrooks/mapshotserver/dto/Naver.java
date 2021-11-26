package com.joebrooks.mapshotserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Naver {
    private Coor center;
    private String key;
    private MapType mapType;
    private Integer width;
    private Integer height;
}
