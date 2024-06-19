package com.faust.pronghorn.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by FaustineP on 03/04/2017.
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.pccw.ad.pronghorn.model.*")
@EntityScan("com.pccw.ad.pronghorn.model")
public class Application {

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
}
