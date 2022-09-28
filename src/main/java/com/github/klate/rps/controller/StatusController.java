package com.github.klate.rps.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StatusController {

    // TODO: replace this with the Spring boot actuator module

    @GetMapping("/")
    public String index() {
        return "Application online";
    }


}
