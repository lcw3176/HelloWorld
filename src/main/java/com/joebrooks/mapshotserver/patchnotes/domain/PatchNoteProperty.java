package com.joebrooks.mapshotserver.patchnotes.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


//@Component
//@ConfigurationProperties(prefix = "patch")
public class PatchNoteProperty {
    private List<Content> contents = new ArrayList<>();


    public List<Content> getContents(){
        return this.contents;
    }
}
