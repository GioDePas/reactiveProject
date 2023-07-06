package com.reactiveProject.service;

import com.reactiveProject.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReviews(long bookId) {
        var reviews = List.of(
                new Review(1L, bookId, 4.5, "Good"),
                new Review(2L, bookId, 4.0, "Nice"),
                new Review(3L, bookId, 3.5, "Average")
        );
        return Flux.fromIterable(reviews);
    }

}
