package com.joebrooks.mapshotserver.home.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
public class Coor {

    @NotBlank
    @Positive
    private float lat;

    @NotBlank
    @Positive
    private float lng;
}
