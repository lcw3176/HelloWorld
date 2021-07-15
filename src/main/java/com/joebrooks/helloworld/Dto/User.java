package com.joebrooks.helloworld.Dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class User {
    public static HashMap<String, User> userList = new HashMap<>();
    private String nickName;
    private String ip;
}
