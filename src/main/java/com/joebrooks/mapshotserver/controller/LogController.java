package com.joebrooks.mapshotserver.controller;

import com.joebrooks.mapshotserver.domain.OnFailed;
import com.joebrooks.mapshotserver.domain.OnSuccess;
import com.joebrooks.mapshotserver.service.SendLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://testservermapshot.netlify.app")
@RestController
public class LogController {

    private final SendLogService sendLogService;

    public LogController(SendLogService sendLogService){
        this.sendLogService = sendLogService;
    }

    @RequestMapping("/log/success")
    @PostMapping
    public ResponseEntity onSuccess(@RequestBody OnSuccess onSuccess){

        try{
            sendLogService.success(onSuccess);
        } catch (Exception e){
            ResponseEntity.badRequest().build();
        }


        return ResponseEntity.ok().build();
    }

    @RequestMapping("/log/failed")
    @PostMapping
    public ResponseEntity onFailed(@RequestBody OnFailed onFailed){

        try{
            sendLogService.failed(onFailed);
        } catch (Exception e){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
