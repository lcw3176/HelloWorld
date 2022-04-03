package com.joebrooks.mapshotserver.global.logging;

import com.joebrooks.mapshotserver.global.exception.ExceptionMessage;

public interface IMessageClient {
    void sendMessage(ExceptionMessage error);
}
