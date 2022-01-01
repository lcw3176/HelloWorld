package com.joebrooks.mapshotserver.domain.slack.controller;

import com.joebrooks.mapshotserver.domain.slack.dto.OnFailed;
import com.joebrooks.mapshotserver.domain.slack.dto.OnSuccess;
import com.joebrooks.mapshotserver.domain.slack.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://testservermapshot.netlify.app")
@RestController
@RequiredArgsConstructor
public class LogController {

    private final SlackService slackService;

    @RequestMapping("/log/success")
    @PostMapping
    public ResponseEntity onSuccess(@RequestBody OnSuccess onSuccess){

        try{
            slackService.onSuccess(onSuccess);
        } catch (Exception e){
            ResponseEntity.badRequest().build();
        }


        return ResponseEntity.ok().build();
    }

    @RequestMapping("/log/failed")
    @PostMapping
    public ResponseEntity onFailed(@RequestBody OnFailed onFailed){

        try{
            slackService.onFailed(onFailed);
        } catch (Exception e){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
