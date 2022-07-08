package com.igaurav;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Singleton
public class RecommendationService {

    private static final Logger log = LoggerFactory.getLogger(RecommendationService.class);

    @Inject
    EmbeddedServer server;

    public Single<List<Product>> getRecommendations(List<String> ids) throws MalformedURLException {
        log.info("RecommendationController.getRecommendations() called...");
        RxHttpClient client = RxHttpClient.create(new URL("http://" + server.getHost() + ":" + 9007));
        log.info("Thread Service: " + Thread.currentThread().getName());
        //Single<Product> s = client.retrieve(HttpRequest.GET("/persons/reactive"), Product.class).firstOrError();
        return Observable.fromIterable(ids)
                .flatMap(id ->client.retrieve(HttpRequest.GET("/product/"+id), Product.class)
                        .firstOrError()
                        .toObservable())
                .toList();
    }
}
