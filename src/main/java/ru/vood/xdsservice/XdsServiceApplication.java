package ru.vood.xdsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class XdsServiceApplication {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(XdsServiceApplication.class, args);
        new XmlGenerator().run(args);
    }

}
