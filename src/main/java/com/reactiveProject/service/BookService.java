package com.reactiveProject.service;

import com.reactiveProject.domain.Book;
import com.reactiveProject.domain.Review;
import com.reactiveProject.exception.BookException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookInfoService bookInfoService;
    private final ReviewService reviewService;

    public Flux<Book> getBooks() {
        var books = bookInfoService.getBooks();
        return books.flatMap(bookInfo -> {
            Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
            return reviews.map(reviewList -> new Book(bookInfo, reviewList));
        }).onErrorMap(throwable -> {
            log.error("Exception is: ", throwable);
            return new BookException("Exception while fetching books");
        }).log();
    }

    public Flux<Book> getBooksRetry() {
        var books = bookInfoService.getBooks();
        return books.flatMap(bookInfo -> {
            Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
            return reviews.map(reviewList -> new Book(bookInfo, reviewList));
        }).onErrorMap(throwable -> {
            log.error("Exception is: ", throwable);
            return new BookException("Exception while fetching books");
        }).retry(3).log();
    }

    public Flux<Book> getBooksRetryWhen() {
        var books = bookInfoService.getBooks();
        return books.flatMap(bookInfo -> {
            Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
            return reviews.map(reviewList -> new Book(bookInfo, reviewList));
        }).onErrorMap(throwable -> {
            log.error("Exception is: ", throwable);
            return new BookException("Exception while fetching books");
        }).retryWhen(getRetryBackoffSpecs()).log();
    }

    private static RetryBackoffSpec getRetryBackoffSpecs() {
        return Retry.backoff(3, Duration.ofMillis(1000))
                .filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure()));
    }

    public Mono<Book> getBookById(long bookId) {
        var bookInfo = bookInfoService.getBookById(bookId);
        var reviews = reviewService.getReviews(bookId).collectList();
        return bookInfo.zipWith(reviews, Book::new);
    }

}
