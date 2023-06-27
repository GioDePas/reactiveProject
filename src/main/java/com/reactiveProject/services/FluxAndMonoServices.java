package com.reactiveProject.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoServices {

    public Mono<String> fruitMono() {
        return Mono.just("MonoApple").log();
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

    public static void main(String[] args) {

        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

        fluxAndMonoServices.fruitMono()
                .subscribe(System.out::println);

        fluxAndMonoServices.fruitsFlux()
                .subscribe(System.out::println);

        fluxAndMonoServices.fruitsFluxMap()
                .subscribe(System.out::println);

        fluxAndMonoServices.fruitsFluxFilter(5)
                .subscribe(System.out::println);

    }

}
