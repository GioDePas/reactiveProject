package com.reactiveProject.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

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

    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("MonoApple")
                .flatMapMany(fruit -> Flux.just(fruit.split("")))
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

    public Flux<String> fruitsFluxTransform(int length) {
        Function<Flux<String>, Flux<String>> filter = fruitFlux -> fruitFlux
                .filter(fruit -> fruit.length() > length);
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange"))
                .transform(filter)
                .log();
    }

    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int length) {
        Function<Flux<String>, Flux<String>> filter = fruitFlux -> fruitFlux
                .filter(fruit -> fruit.length() > length);
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange"))
                .transform(filter)
                .defaultIfEmpty("Default")
                .log();
    }

    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int length) {
        Function<Flux<String>, Flux<String>> filter = fruitFlux -> fruitFlux
                .filter(fruit -> fruit.length() > length);
        return Flux.fromIterable(List.of("Apple", "Banana", "Orange"))
                .transform(filter)
                .switchIfEmpty(Flux.just("Pineapple", "Jack Fruit"))
                .transform(filter)
                .log();
    }

    public Flux<String> fruitsFluxConcat() {
        var fruits = Flux.just("Apple", "Orange");
        var vegetables = Flux.just("Carrot", "Tomato");
        return Flux.concat(fruits, vegetables).log();
    }

    public Flux<String> fruitsFluxConcatWith() {
        var fruits = Flux.just("Apple", "Orange");
        var vegetables = Flux.just("Carrot", "Tomato");
        return fruits.concatWith(vegetables).log();
    }

    public Flux<String> fruitsMonoConcatWith() {
        var fruits = Mono.just("Apple");
        var vegetables = Mono.just("Carrot");
        return fruits.concatWith(vegetables).log();
    }

    public Flux<String> fruitsFluxMerge() {
        var fruits = Flux.just("Apple", "Orange")
                .delayElements(Duration.ofMillis(50));
        var vegetables = Flux.just("Carrot", "Tomato")
                .delayElements(Duration.ofMillis(75));
        return Flux.merge(fruits, vegetables);
    }

    public Flux<String> fruitsFluxMergeWith() {
        var fruits = Flux.just("Apple", "Orange")
                .delayElements(Duration.ofMillis(50));
        var vegetables = Flux.just("Carrot", "Tomato")
                .delayElements(Duration.ofMillis(75));
        return fruits.mergeWith(vegetables);
    }

    public Flux<String> fruitsFluxMergeSequential() {
        var fruits = Flux.just("Apple", "Orange")
                .delayElements(Duration.ofMillis(50));
        var vegetables = Flux.just("Carrot", "Tomato")
                .delayElements(Duration.ofMillis(75));
        return Flux.mergeSequential(fruits, vegetables);
    }

    public Flux<String> fruitsFluxZip() {
        var fruits = Flux.just("Apple", "Orange");
        var vegetables = Flux.just("Carrot", "Tomato");
        return Flux.zip(fruits, vegetables, (f, v) -> f + " " + v).log();
    }

    public Flux<String> fruitsFluxZipWith() {
        var fruits = Flux.just("Apple", "Orange");
        var vegetables = Flux.just("Carrot", "Tomato");
        return fruits.zipWith(vegetables, (f, v) -> f + " " + v).log();
    }

    public static void main(String[] args) {

        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

        fluxAndMonoServices.fruitMono()
                .subscribe(System.out::println);

        fluxAndMonoServices.fruitsFlux()
                .subscribe(System.out::println);

    }

}
