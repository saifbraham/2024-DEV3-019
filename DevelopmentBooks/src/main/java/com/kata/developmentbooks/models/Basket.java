package com.kata.developmentbooks.models;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    private Map<Book, Integer> basket = new HashMap<>();

    public Map<Book, Integer> getBasket() {
        return basket;
    }

    public void addToBasket(Book book, int quantity) {
        this.basket.put(book,quantity);
    }
}
