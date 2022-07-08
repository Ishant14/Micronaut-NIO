package com.igaurav;

import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {

//      var flow=    Flowable.fromArray(new String[]{"a", "b", "c"});
//
//
//        flow
//                .map(item ->{
//            System.out.println("flowwww");
//            return item.toUpperCase();})
//        .subscribe(i -> System.out.println(i));
        Micronaut.run(Application.class, args);
    }
}
