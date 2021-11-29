package com.joebrooks.mapshotserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patch-notes")
public class PatchNotesController {

    @GetMapping
    public String patchNotes(){
        return "patch-notes/index";
    }
}
