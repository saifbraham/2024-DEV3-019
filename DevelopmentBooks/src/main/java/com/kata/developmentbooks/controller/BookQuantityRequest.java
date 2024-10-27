package com.kata.developmentbooks.controller;

import com.kata.developmentbooks.models.Book;

import java.util.Map;

public class BookQuantityRequest {

    private Map<Book, Integer> bookQuantities;

    // Getter and Setter
    public Map<Book, Integer> getBookQuantities() {
        return bookQuantities;
    }

    public void setBookQuantities(Map<Book, Integer> bookQuantities) {
        this.bookQuantities = bookQuantities;
    }
}
