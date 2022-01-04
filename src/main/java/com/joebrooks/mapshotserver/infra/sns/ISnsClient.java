package com.joebrooks.mapshotserver.infra.sns;

public interface ISnsClient {
    void sendMessage(String message);
}
