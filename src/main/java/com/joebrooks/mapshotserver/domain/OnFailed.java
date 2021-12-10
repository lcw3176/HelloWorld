package com.joebrooks.mapshotserver.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OnFailed {
    private String title;
    private String message;
    private int usingCount;
    private String usedFunc;
}
