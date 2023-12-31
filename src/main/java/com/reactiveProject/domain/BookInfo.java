package com.reactiveProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {

    private Long bookId;

    private String title;

    private String author;

    private String ISBN;

}
