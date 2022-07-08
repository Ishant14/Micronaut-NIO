package com.igaurav;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

@Controller("/recommendations")
final class RecommendationController {

    private static final Logger log = LoggerFactory.getLogger(RecommendationController.class);

    private static final List<String> ids = Arrays.asList(
            "PROD-001",
            "PROD-002"
    );

    private final ProductClient productClient;

    @Inject
    RecommendationService recommendationService;

    public RecommendationController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Get
    public Single<List<Product>> getRecommendations() throws MalformedURLException {
        log.info("Calling Thread : " + Thread.currentThread().getName());
        Single<List<Product>> products  = recommendationService.getRecommendations(ids);
        log.info("Response Thread : " + Thread.currentThread().getName());
        return products;
    }

    @Get("/rec")
    public String getRecommendationsSimple() throws MalformedURLException {
        log.info("Response Thread : " + Thread.currentThread().getName());
        return "Recommended String";
    }

}
