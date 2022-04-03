package com.joebrooks.mapshotserver.global.logging;

import com.joebrooks.mapshotserver.global.exception.ExceptionResponse;

public interface IMessageClient {
    void sendMessage(ExceptionResponse error);
}
