package com.joebrooks.mapshotserver.patchnotes;

import com.joebrooks.mapshotserver.patchnotes.domain.Content;
import com.joebrooks.mapshotserver.patchnotes.domain.PatchNoteProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PatchNotesProepertyTest {
    @Autowired
    PatchNoteProperty patchNotesProeperty;

    @Test
    public void 패치노트_목록_가져오기(){
        System.out.println(patchNotesProeperty.getContents());

    }
}
