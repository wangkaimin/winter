package com.springio.winter.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wangkm
 * @date 2018-09-28
 * @since 0.0.1
 */
@RestController
public class DemoController {

    @Autowired
    GreetingService greetingService;

    @GetMapping("get")
    public String get(){
        return greetingService.getGreeting();
    }

}
