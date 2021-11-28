package com.joebrooks.mapshotserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tile {
    private Coor coor;
    private int width;
    private int height;
    private String key;
}
