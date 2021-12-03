package com.joebrooks.mapshotserver.home.domain;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class UserConfig {

    @Digits(integer = 40, fraction = 40)
    @Positive
    private float lat;

    @Digits(integer = 140, fraction = 140)
    @Positive
    private float lng;

    @NotBlank
    private String mapType;

    @NotBlank
    private String provider;

    @NotBlank
    private String radius;
}
