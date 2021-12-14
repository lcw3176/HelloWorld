package com.joebrooks.mapshotserver.controller;

import com.joebrooks.mapshotserver.domain.OnFailed;
import com.joebrooks.mapshotserver.domain.OnSuccess;
import com.joebrooks.mapshotserver.service.UserLoggingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://testservermapshot.netlify.app")
@RestController
public class LogController {

    private UserLoggingService userLoggingService;

    public LogController(UserLoggingService userLoggingService){
        this.userLoggingService = userLoggingService;
    }

    @RequestMapping("/log/success")
    @PostMapping
    public ResponseEntity onSuccess(@RequestBody OnSuccess onSuccess){

        try{
            userLoggingService.success(onSuccess);

        } catch (Exception e){

            ResponseEntity.badRequest().build();
        }


        return ResponseEntity.ok().build();
    }

    @RequestMapping("/log/failed")
    @PostMapping
    public ResponseEntity onFailed(@RequestBody OnFailed onFailed){

        try{
            userLoggingService.failed(onFailed);

        } catch (Exception e){

            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
