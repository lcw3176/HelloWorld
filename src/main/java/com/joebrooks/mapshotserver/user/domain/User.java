package com.joebrooks.mapshotserver.user.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class User {

    @NotBlank
    private boolean isFirstVisit;

    @NotBlank
    private int visitedCount;

}
