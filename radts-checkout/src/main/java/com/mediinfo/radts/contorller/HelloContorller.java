package com.mediinfo.radts.contorller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class HelloContorller {

    /**
     * 获取application.yml中的配置
     */
    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        log.debug("debug....");
        log.info("info....");
        log.error("error....");
        return port;
    }
}
