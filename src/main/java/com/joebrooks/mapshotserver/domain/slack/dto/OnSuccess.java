package com.joebrooks.mapshotserver.domain.slack.dto;

import lombok.Data;

@Data
public class OnSuccess {
    private int usingCount;
    private String usedFunc;
}
