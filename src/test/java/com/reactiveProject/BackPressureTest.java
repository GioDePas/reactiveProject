package com.reactiveProject;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {

    @Test
    public void testBackPressure() {
        var numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("Number is: " + integer));
        numbers.subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("Number is: " + value);
                if (value == 3) cancel();
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Completed");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }
        });
    }

    @Test
    public void testBackPressureDrop() {
        var numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("Number is: " + integer));
        numbers
                .onBackpressureDrop(integer -> System.out.println("Dropping: " + integer))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Number is: " + value);
                        if (value == 3) hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }

    @Test
    public void testBackPressureBuffer() {
        var numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("Number is: " + integer));
        numbers
                .onBackpressureBuffer(10, integer -> System.out.println("Dropping: " + integer))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Number is: " + value);
                        if (value == 3) hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }

    @Test
    public void testBackPressureError() {
        var numbers = Flux.range(1, 100).log();
        //numbers.subscribe(integer -> System.out.println("Number is: " + integer));
        numbers
                .onBackpressureError()
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Number is: " + value);
                        if (value == 3) hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        System.out.println("Error: " + throwable.getMessage());
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }

}
