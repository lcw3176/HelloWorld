package com.joebrooks.mapshotserver.global.exception;

public enum ExceptionType {
    ClientDisconnected("Client Disconnected before work's done"),
    ServerTimeOut("Heroku Server TimeOut: KakaoMap");

    private String description;

    private ExceptionType(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
