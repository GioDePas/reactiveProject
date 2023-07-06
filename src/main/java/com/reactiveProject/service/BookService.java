package com.reactiveProject.service;

import com.reactiveProject.domain.Book;
import com.reactiveProject.domain.Review;
import com.reactiveProject.exception.BookException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookInfoService bookInfoService;
    private final ReviewService reviewService;

    public Flux<Book> getBooks() {
        var books = bookInfoService.getBooks();
        return books
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews.map(reviewList -> new Book(bookInfo, reviewList));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: ", throwable);
                    return new BookException("Exception while fetching books");
                })
                .log();
    }

    public Mono<Book> getBookById(long bookId) {
        var bookInfo = bookInfoService.getBookById(bookId);
        var reviews = reviewService.getReviews(bookId).collectList();
        return bookInfo.zipWith(reviews, Book::new);
    }

}
