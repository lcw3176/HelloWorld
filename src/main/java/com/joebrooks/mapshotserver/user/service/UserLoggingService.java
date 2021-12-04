package com.joebrooks.mapshotserver.user.service;

import com.joebrooks.mapshotserver.user.exception.InvalidUserException;
import org.springframework.stereotype.Service;

@Service
public class UserLoggingService {

    public void FirstVisit(){
        throw new InvalidUserException("ssss");
    }

    public void MultipleVisit(){

    }
}
