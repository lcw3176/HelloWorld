package com.joebrooks.mapshotserver.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void 유저_카운트_테스트() throws Exception {
        String result = "501";

        for(int i = 0; i < 500; i++){
            MvcResult temp = mvc.perform(post("/analysis/user"))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        mvc.perform(post("/analysis/user"))
                .andExpect(status().isOk())
                .andExpect(content().string(result));

    }

}
