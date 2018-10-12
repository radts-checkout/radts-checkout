package com.mediinfo.radts.contorller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class HelloContorller {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello, this is radts!";
    }
}
