package com.joebrooks.helloworld.Dto;

import com.joebrooks.helloworld.Common.ChatCommand;
import lombok.Data;

@Data
public class Chat {
    private int command;
    private String content;
    private String id;
}
