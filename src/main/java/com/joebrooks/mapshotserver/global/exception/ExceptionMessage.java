package com.joebrooks.mapshotserver.global.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionMessage {
    private String name;
    private String message;
}
