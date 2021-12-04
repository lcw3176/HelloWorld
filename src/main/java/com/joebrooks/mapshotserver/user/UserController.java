package com.joebrooks.mapshotserver.user;

import com.joebrooks.mapshotserver.user.service.UserLoggingService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
public class UserController {

    private AtomicInteger userCount = new AtomicInteger(0);
    private UserLoggingService userLoggingService;

    public UserController(UserLoggingService userLoggingService){
        this.userLoggingService = userLoggingService;
    }


//    @Validated @RequestBody User user
    @GetMapping
    public ResponseEntity increaseUserCount(){
        try{
            userLoggingService.FirstVisit();
        } catch (RuntimeException e){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

}
