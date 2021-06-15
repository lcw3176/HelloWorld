package com.joebrooks.helloworld.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.joebrooks.helloworld.Common.ChatCommand;
import com.joebrooks.helloworld.Dto.Chat;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    public String MakeJson(ChatCommand command, String id, String content) throws JsonProcessingException {
        Chat chat = new Chat();

        chat.setCommand(command.ordinal());
        chat.setId(id);
        chat.setContent(content);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(chat);
    }
}
