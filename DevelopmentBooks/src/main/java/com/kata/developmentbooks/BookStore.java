package com.kata.developmentbooks;

import com.kata.developmentbooks.models.Basket;
import com.kata.developmentbooks.models.Book;

public class BookStore {

    private Basket basket = new Basket();

    public void addQuantityOfBookToBasket(Book book, int quantity) {
        basket.addToBasket(book, quantity);
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

}
