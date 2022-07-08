package com.igaurav;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Singleton;

@Singleton
public class Service {

    Logger logger = LoggerFactory.getLogger(Service.class);
    public String sayHello(){
        logger.info("Thread " + Thread.currentThread().getName());
        //logger.info("sayHello()");
        latency(2000);
        //logger.info("sayhello() waken");
        return "hello";
    }

    private void latency(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Flowable<String> sayHelloReactive(){
        logger.info("sayHello reactive service ()");
//        Single<List<Resources>>
        var res =    Flowable.fromArray(new String [] {"ABC","XYZ"})//Single.just(1)
                .subscribeOn(Schedulers.io())
                .map(it ->{
                    latency(2000); //dbcall("abc")
                    logger.info("IT " + it);
                    logger.info("Returning in reactive");
                    return "hello Reactive";
                });

        logger.info("reactive() waken");
        return res;
    }
}
