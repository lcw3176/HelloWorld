package com.joebrooks.mapshotserver.dto;

import lombok.Builder;

@Data
@Builder
public class Coor {
    private float lat;
    private float lng;
}
