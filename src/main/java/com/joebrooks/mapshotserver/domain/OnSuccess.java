package com.joebrooks.mapshotserver.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OnSuccess {
    private int usingCount;
    private String usedFunc;
}
