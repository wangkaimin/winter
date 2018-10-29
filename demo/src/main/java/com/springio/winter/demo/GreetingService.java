package com.springio.winter.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author wangkm
 * @date 2018-09-30
 * @since 0.0.1
 */
@Service
public class GreetingService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProducerService producerService;

    private static Logger logger  = LoggerFactory.getLogger(GreetingService.class);

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getGreeting() {
        logger.info("getGreeting " + Thread.currentThread().toString());
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("spring-producer");
        return producerService.get();
//        return new RestTemplate().getForObject("http://spring-producer:9081/get", String.class);
    }

    private String defaultGreeting() {
        logger.info("defaultGreeting " + Thread.currentThread().toString());
        return "Hello demo!";
    }
}
