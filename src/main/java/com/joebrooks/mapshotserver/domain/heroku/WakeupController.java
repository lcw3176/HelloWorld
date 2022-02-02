package com.joebrooks.mapshotserver.domain.heroku;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("https://mapshot.netlify.app")
@RestController
@RequestMapping("/heroku/wakeup")
@RequiredArgsConstructor
public class WakeupController {

    @GetMapping
    public ResponseEntity wakeUp(){

        return ResponseEntity.ok().build();
    }
}
