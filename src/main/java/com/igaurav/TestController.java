package com.igaurav;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/test")
public class TestController {

    private Service service;

    public TestController(Service service) {
        this.service = service;
    }

    Logger logger = LoggerFactory.getLogger(TestController.class);
    @Get(value = "/hello")
    String hello(){
        //logger.info("hello()");
        logger.info("Thread Name : " + Thread.currentThread().getName());
        return service.sayHello();
    }

    @Get(value = "/hello1", produces = MediaType.APPLICATION_JSON)
    Flowable<String> hello1(){
        logger.info("hello1()");
        var res = service.sayHelloReactive();
        logger.info("hello1 calll");
        return res;
    }
}
