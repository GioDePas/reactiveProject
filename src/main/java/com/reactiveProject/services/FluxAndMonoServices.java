package com.reactiveProject.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoServices {

    public Mono<String> fruitMono() {
        return Mono.just("MonoApple").log();
    }

    public Mono<List<String>> fruitMonoFlatMap() {
        return Mono.just("MonoApple")
                .flatMap(fruit -> Mono.just(List.of(fruit.split(""))))
                .log();
    }

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange")).log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange"))
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFilter(int length) {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange"))
                .filter(fruit -> fruit.length() > length);
    }

    public Flux<String> fruitsFluxFilterMap(int length) {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange"))
                .filter(fruit -> fruit.length() > length)
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange"))
                .flatMap(fruit -> Flux.just(fruit.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange"))
                .flatMap(fruit -> Flux.just(fruit.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }

    public Flux<String> fruitsFluxConcatMap() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange"))
                .concatMap(fruit -> Flux.just(fruit.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }

    public static void main(String[] args) {

        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

        fluxAndMonoServices.fruitMono()
                .subscribe(System.out::println);

        fluxAndMonoServices.fruitsFlux()
                .subscribe(System.out::println);

    }

}
