package com.reactiveProject.service;

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

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {
        var fruitsFluxTransformDefaultIfEmpty = fluxAndMonoServices.fruitsFluxTransformDefaultIfEmpty(10);
        StepVerifier.create(fruitsFluxTransformDefaultIfEmpty)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitsFluxTransformSwitchIfEmpty = fluxAndMonoServices.fruitsFluxTransformSwitchIfEmpty(8);
        StepVerifier.create(fruitsFluxTransformSwitchIfEmpty)
                .expectNext("Pineapple", "Jack Fruit")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitsFluxConcat = fluxAndMonoServices.fruitsFluxConcat().log();
        StepVerifier.create(fruitsFluxConcat)
                .expectNext("Apple", "Orange", "Carrot", "Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitsFluxConcatWith = fluxAndMonoServices.fruitsFluxConcatWith().log();
        StepVerifier.create(fruitsFluxConcatWith)
                .expectNext("Apple", "Orange", "Carrot", "Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {
        var fruitsMonoConcatWith = fluxAndMonoServices.fruitsMonoConcatWith().log();
        StepVerifier.create(fruitsMonoConcatWith)
                .expectNext("Apple", "Carrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitsFluxMerge = fluxAndMonoServices.fruitsFluxMerge().log();
        StepVerifier.create(fruitsFluxMerge)
                .expectNext("Apple", "Carrot", "Orange", "Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitsFluxMergeWith = fluxAndMonoServices.fruitsFluxMergeWith().log();
        StepVerifier.create(fruitsFluxMergeWith)
                .expectNext("Apple", "Carrot", "Orange", "Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeSequential() {
        var fruitsFluxMergeSequential = fluxAndMonoServices.fruitsFluxMergeSequential().log();
        StepVerifier.create(fruitsFluxMergeSequential)
                .expectNext("Apple", "Orange", "Carrot", "Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitsFluxZip = fluxAndMonoServices.fruitsFluxZip().log();
        StepVerifier.create(fruitsFluxZip)
                .expectNext("Apple Carrot", "Orange Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitsFluxZipWith = fluxAndMonoServices.fruitsFluxZipWith().log();
        StepVerifier.create(fruitsFluxZipWith)
                .expectNext("Apple Carrot", "Orange Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitsFluxZipTuple = fluxAndMonoServices.fruitsFluxZipTuple().log();
        StepVerifier.create(fruitsFluxZipTuple)
                .expectNext("Apple Carrot Dog", "Orange Tomato Cat")
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipWith() {
        var fruitsMonoZipWith = fluxAndMonoServices.fruitsMonoZipWith().log();
        StepVerifier.create(fruitsMonoZipWith)
                .expectNext("Apple Carrot")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterDoOn() {
        var fruitsFluxFilterDoOn = fluxAndMonoServices.fruitsFluxFilterDoOn(5);
        StepVerifier.create(fruitsFluxFilterDoOn)
                .expectNext("Banana", "Orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturn() {
        var fruitsFluxOnErrorReturn = fluxAndMonoServices.fruitsFluxOnErrorReturn();
        StepVerifier.create(fruitsFluxOnErrorReturn)
                .expectNext("Apple", "Orange", "Default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruitsFluxOnErrorContinue = fluxAndMonoServices.fruitsFluxOnErrorContinue().log();
        StepVerifier.create(fruitsFluxOnErrorContinue)
                .expectNext("APPLE", "ORANGE")
                .verifyComplete();

    }

    @Test
    void fruitsFluxOnErrorMap() {
        var fruitsFluxOnErrorMap = fluxAndMonoServices.fruitsFluxOnErrorMap().log();
        StepVerifier.create(fruitsFluxOnErrorMap)
                .expectNext("APPLE")
                .expectError(IllegalStateException.class)
                .verify();
    }

}