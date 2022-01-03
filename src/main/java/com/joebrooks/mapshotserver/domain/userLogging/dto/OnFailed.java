package com.joebrooks.mapshotserver.domain.userLogging.dto;

import lombok.Data;

@Data
public class OnFailed {
    private String title;
    private String message;
    private int usingCount;
    private String usedFunc;
}
