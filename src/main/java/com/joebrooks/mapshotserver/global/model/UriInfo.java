package com.joebrooks.mapshotserver.global.model;

public enum UriInfo {
    scheme("https"),
    host("mapshot.herokuapp.com"),
    kakaoMapCrawler("/map/kakao/crawl");

    private String param;

    private UriInfo(String param){
        this.param = param;
    }

    @Override
    public String toString() {
        return this.param;
    }
}
