package com.joebrooks.mapshotserver.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OnFailed {
    private String title;
    private String message;
    private boolean firstVisit;
    private int usingCount;
    private Date date;
    private String usedFunc;
}
