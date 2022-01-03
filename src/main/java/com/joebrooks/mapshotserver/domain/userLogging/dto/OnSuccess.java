package com.joebrooks.mapshotserver.domain.userLogging.dto;

import lombok.Data;

@Data
public class OnSuccess {
    private int usingCount;
    private String usedFunc;
}
