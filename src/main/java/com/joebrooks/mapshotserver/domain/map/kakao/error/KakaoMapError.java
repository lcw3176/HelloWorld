package com.joebrooks.mapshotserver.domain.map.kakao.error;

public enum KakaoMapError {
    ClientDisconnected(1000, "clientAbortException"),
    ServerTimeOut(1001, "Heroku Server TimeOut: KakaoMap");

    private int code;
    private String description;

    private KakaoMapError(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }

    public String getDescription(){
        return this.description;
    }
}
