package com.joebrooks.mapshotserver.home.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coor {
    private float lat;
    private float lng;
}
