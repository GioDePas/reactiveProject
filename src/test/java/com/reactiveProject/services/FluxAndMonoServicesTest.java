package com.reactiveProject.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void fruitMono() {
        var fruitMono = fluxAndMonoServices.fruitMono();
        StepVerifier.create(fruitMono)
                .expectNext("MonoApple")
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMap() {
        var fruitMonoFlatMap = fluxAndMonoServices.fruitMonoFlatMap();
        StepVerifier.create(fruitMonoFlatMap)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();
        StepVerifier.create(fruitsFlux)
                .expectNext("Apple", "Banana", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitsFluxMap = fluxAndMonoServices.fruitsFluxMap();
        StepVerifier.create(fruitsFluxMap)
                .expectNext("APPLE", "BANANA", "ORANGE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFluxFilter = fluxAndMonoServices.fruitsFluxFilter(5);
        StepVerifier.create(fruitsFluxFilter)
                .expectNext("Banana", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        var fruitsFluxFilterMap = fluxAndMonoServices.fruitsFluxFilterMap(5);
        StepVerifier.create(fruitsFluxFilterMap)
                .expectNext("BANANA", "ORANGE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var fruitsFluxFlatMap = fluxAndMonoServices.fruitsFluxFlatMap();
        StepVerifier.create(fruitsFluxFlatMap)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var fruitsFluxFlatMapAsync = fluxAndMonoServices.fruitsFluxFlatMapAsync();
        StepVerifier.create(fruitsFluxFlatMapAsync)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        var fruitsFluxConcatMap = fluxAndMonoServices.fruitsFluxConcatMap();
        StepVerifier.create(fruitsFluxConcatMap)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany() {
        var fruitMonoFlatMapMany = fluxAndMonoServices.fruitMonoFlatMapMany();
        StepVerifier.create(fruitMonoFlatMapMany)
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var fruitsFluxTransform = fluxAndMonoServices.fruitsFluxTransform(5);
        StepVerifier.create(fruitsFluxTransform)
                .expectNext("Banana", "Orange")
                .verifyComplete();
    }
}