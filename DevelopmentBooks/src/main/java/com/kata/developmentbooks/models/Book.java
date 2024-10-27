package com.kata.developmentbooks.models;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private final String bookTitle;
    private final String bookAuthor;
    private final int publishYear;

    public Book(String bookTitle, String bookAuthor, int publishYear) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publishYear = publishYear;
    }

    // Override equals and hashCode for use as a Map key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publishYear == book.publishYear &&
                Objects.equals(bookTitle, book.bookTitle) &&
                Objects.equals(bookAuthor, book.bookAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle, bookAuthor, publishYear);
    }

}

