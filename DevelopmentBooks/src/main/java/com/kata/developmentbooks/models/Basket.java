package com.kata.developmentbooks.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Basket {

    private Map<Book, Integer> basket = new HashMap<>();
    private List<Integer> quantities = new ArrayList<>(); // List to preserve order

    public Map<Book, Integer> getBasket() {
        return basket;
    }

    public List<Integer> getQuantities() {
        return new ArrayList<>(quantities); // Return a copy of the list
    }

    public void addToBasket(Book book, int quantity) {
        this.basket.put(book, quantity);
        this.quantities.add(quantity); // Store the quantity in the list
    }

    public void clear() {
        this.basket.clear();
        this.quantities.clear(); // Clear the quantities list
    }

}
