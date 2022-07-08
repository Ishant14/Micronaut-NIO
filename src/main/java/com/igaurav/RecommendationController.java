package com.igaurav;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Controller("/recommendations")
final class RecommendationController {

    private static final Logger log = LoggerFactory.getLogger(RecommendationController.class);

    private static final List<String> ids = Arrays.asList(
            "PROD-001",
            "PROD-002",
            "PROD-003",
            "PROD-004"
    );

    private final ProductClient productClient;

    @Inject
    RecommendationService recommendationService;

    public RecommendationController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Get
    public Single<List<Product>> getRecommendations() throws MalformedURLException {
        log.info("Thread : " + Thread.currentThread().getName());
        return recommendationService.getRecommendations(ids);
    }

    @Get("/rec")
    public String getRecommendationsSimple() throws MalformedURLException {
        log.info("Thread : " + Thread.currentThread().getName());
        return "Recommended String";
    }

}
