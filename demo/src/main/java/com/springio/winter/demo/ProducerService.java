package com.springio.winter.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO
 *
 * @author wangkm
 * @date 2018-10-18
 * @since 0.0.1
 */
@FeignClient(value = "spring-producer")
public interface ProducerService {
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    String get();
}
