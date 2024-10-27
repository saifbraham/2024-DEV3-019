package com.kata.developmentbooks.controllers;

import java.util.Map;

public class BookQuantityRequest {

    private Map<String, Integer> bookQuantities;

    // Getter and Setter
    public Map<String, Integer> getBookQuantities() {
        return bookQuantities;
    }

    public void setBookQuantities(Map<String, Integer> bookQuantities) {
        this.bookQuantities = bookQuantities;
    }
}
