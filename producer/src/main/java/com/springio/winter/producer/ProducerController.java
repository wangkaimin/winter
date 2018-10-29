package com.springio.winter.producer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wangkm
 * @date 2018-09-30
 * @since 0.0.1
 */
@RestController
public class ProducerController {

    private static Logger logger  = LoggerFactory.getLogger(ProducerController.class);

    @GetMapping
    @HystrixCommand(fallbackMethod = "defaultGreeting",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
                    @HystrixProperty(name="execution.isolation.semaphore.maxConcurrentRequests", value="5")
            })
    String get(){
        logger.info("producer get");
        return "Hello Producer!";
    }

    private String defaultGreeting() {
        logger.info("defaultGreeting " + Thread.currentThread().toString());
        return "Hello Producer defaultGreeting!";
    }

}
