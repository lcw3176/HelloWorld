package com.joebrooks.mapshotserver.valueTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location=classpath:/radius.yml" })
public class RadiusTest {

    @Value("${Radius.One.sideBlockCount}")
    private int sideBlockValue;

    @Value("${Radius.One.zoom}")
    private int zoomValue;


    @Test
    public void yml_읽어오기_타일갯수(){
        final int oneSideBlockCount = 11;
        assertEquals(oneSideBlockCount, sideBlockValue);
    }

    @Test
    public void yml_읽어오기_줌레벨(){
        final int oneZoom = 18;
        assertEquals(oneZoom, zoomValue);
    }

}
