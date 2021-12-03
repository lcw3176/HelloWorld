package com.joebrooks.mapshotserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;



@SpringBootTest
@TestPropertySource(locations= "classpath:additional/radius.yml")
class MapshotServerApplicationTests {


    @Test
    void contextLoads() {

    }

}
