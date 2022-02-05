package com.joebrooks.mapshotserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
//@EnableAspectJAutoProxy
public class MapshotServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapshotServerApplication.class, args);
    }

}
