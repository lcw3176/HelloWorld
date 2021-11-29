package com.joebrooks.mapshotserver.home.dto;

import com.joebrooks.mapshotserver.home.datatype.CommonMapType;
import com.joebrooks.mapshotserver.home.datatype.MapProviderType;
import com.joebrooks.mapshotserver.home.datatype.RadiusType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class Map {
    @NotBlank
    private MapProviderType provider;

    @NotBlank
    private CommonMapType mapType;

    @NotBlank
    private RadiusType radiusType;

    @NotBlank
    private Coor centerCoor;

}
