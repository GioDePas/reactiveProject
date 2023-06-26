package com.reactiveProject.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoServices {

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange")).log();
    }

    public Mono<String> fruitMono() {
        return Mono.just("MonoApple").log();
    }

    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

        fluxAndMonoServices.fruitsFlux()
            .subscribe(System.out::println);

        fluxAndMonoServices.fruitMono()
            .subscribe(System.out::println);
    }

}
