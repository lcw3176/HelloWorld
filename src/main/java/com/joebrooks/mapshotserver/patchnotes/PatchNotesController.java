package com.joebrooks.mapshotserver.patchnotes;

import com.joebrooks.mapshotserver.patchnotes.domain.PatchNoteProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patch-notes")
@AllArgsConstructor
@NoArgsConstructor
public class PatchNotesController {
//    private PatchNoteProperty patchNoteProperty;
    @Value("${patch.contents.title}")
    private String data;

    @GetMapping
    public String patchNotes(Model model){
        System.out.println(data);
        return "patch-notes/index";
    }
}
