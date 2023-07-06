package com.reactiveProject.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookServiceTest {

    private final BookInfoService bookInfoService = new BookInfoService();
    private final ReviewService reviewService = new ReviewService();
    private final BookService bookService = new BookService(bookInfoService, reviewService);

    @Test
    void getBooks() {
        var books = bookService.getBooks();
        StepVerifier.create(books)
                .assertNext(book -> {
                    assertEquals("Book1", book.getBookInfo().getTitle());
                    assertEquals(3, book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book2", book.getBookInfo().getTitle());
                    assertEquals(3, book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book3", book.getBookInfo().getTitle());
                    assertEquals(3, book.getReviews().size());
                })
                .verifyComplete();
    }

    @Test
    void getBookById() {
        var book = bookService.getBookById(1).log();
        StepVerifier.create(book)
                .assertNext(b -> {
                    assertEquals("Book1", b.getBookInfo().getTitle());
                    assertEquals(3, b.getReviews().size());
                })
                .verifyComplete();
    }

}