package com.dotsub.lucas.jefile.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @RequestMapping("/")
    public String index() {
        return "Jefile API";
    }

}
