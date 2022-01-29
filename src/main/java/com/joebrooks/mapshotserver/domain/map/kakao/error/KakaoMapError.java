package com.joebrooks.mapshotserver.domain.map.kakao.error;

public enum KakaoMapError {
    ClientDisconnected("Client Disconnected before work's done"),
    ServerTimeOut("Heroku Server TimeOut: KakaoMap");

    private String description;

    private KakaoMapError(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
