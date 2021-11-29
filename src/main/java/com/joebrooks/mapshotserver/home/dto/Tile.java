package com.joebrooks.mapshotserver.home.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
public class Tile {

    @NotBlank
    private Coor coor;

    @NotBlank
    @Positive
    @Max(value = 1000)
    private int width;

    @NotBlank
    @Positive
    @Max(value = 1000)
    @Min(value = 800)
    private int height;

    @NotBlank
    private String key;
}
