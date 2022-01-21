package com.joebrooks.mapshotserver.global.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private String name;
    private String message;
}
