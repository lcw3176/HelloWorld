package com.joebrooks.mapshotserver.dto;

import lombok.Setter;

@Setter
public class MapType {
    private String[] naver = {
        "basic",
        "satellite",
        "satellite_base",
    };

    private String[] kakao = {
        "ROADMAP",
        "SKYVIEW",
        "HYBRID",
    };

    public enum Company{
        Naver,
        Kakao,
    }

    public enum Type{
        basic,
        skyView,
        hybrid,
    }

    private Company company;
    private String type;

    public void setType(Type type){
        if(company == Company.Naver){
            this.type = naver[type.ordinal()];
        } else if(company == Company.Kakao){
            this.type = kakao[type.ordinal()];
        }
    }

    public String getType(){
        return this.type;
    }


}
