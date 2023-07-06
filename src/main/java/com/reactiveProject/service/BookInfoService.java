package com.reactiveProject.service;

import com.reactiveProject.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getBooks() {
        var books = List.of(
                new BookInfo(1L, "Book1", "Author1", "ISBN1"),
                new BookInfo(2L, "Book2", "Author2", "ISBN2"),
                new BookInfo(3L, "Book3", "Author3", "ISBN3")
        );
        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId) {
        var book = new BookInfo(bookId, "Book1", "Author1", "ISBN1");
        return Mono.just(book);
    }

}
