package com.joebrooks.mapshotserver.infra.sns;

import com.joebrooks.mapshotserver.global.dto.ErrorMessage;

public interface IMessageClient {
    void sendMessage(ErrorMessage error);
}
