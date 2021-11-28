package com.joebrooks.mapshotserver;

import com.joebrooks.mapshotserver.valueTest.RadiusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;



@SpringBootTest
@TestPropertySource(locations="classpath:radius.yml")
class MapshotServerApplicationTests {


    @Test
    void contextLoads() {

    }

}
