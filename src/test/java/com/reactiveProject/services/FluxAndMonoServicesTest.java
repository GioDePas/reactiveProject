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
}