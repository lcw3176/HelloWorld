package com.joebrooks.mapshotserver.user.exception;

public class InvalidUserException extends RuntimeException{

    public InvalidUserException(String wrongValue){
        super("User 잘못된 값 입력: " + wrongValue);
    }
}
