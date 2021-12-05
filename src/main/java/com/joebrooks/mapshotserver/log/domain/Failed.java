package com.joebrooks.mapshotserver.log.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Failed {
    private Date date;
    private String title;
    private String message;
}
