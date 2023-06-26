package com.reactiveProject.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();
        StepVerifier.create(fruitsFlux)
            .expectNext("Apple", "Banana", "Orange")
            .verifyComplete();
    }

    @Test
    void fruitMono() {
        var fruitMono = fluxAndMonoServices.fruitMono();
        StepVerifier.create(fruitMono)
            .expectNext("MonoApple")
            .verifyComplete();
    }

}