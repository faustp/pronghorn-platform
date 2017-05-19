package com.pccw.ad.pronghorn.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by FaustineP on 03/04/2017.
 */
@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
}
