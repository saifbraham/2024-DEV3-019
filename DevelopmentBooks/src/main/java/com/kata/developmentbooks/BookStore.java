package com.kata.developmentbooks;

import com.kata.developmentbooks.models.Basket;
import com.kata.developmentbooks.models.Book;

import java.util.*;

public class BookStore {

    private static final int BOOK_PRICE = 50;
    private static final double DISCOUNT_25_PERCENT = 0.25;
    private static final double DISCOUNT_5_PERCENT = 0.05;
    private static final double DISCOUNT_10_PERCENT = 0.10;

    private Basket basket = new Basket();

    public boolean isAllQuantityAreEqual(Basket basket) {

        Map<Book, Integer> books = basket.getBasket();

        // Check if the Map is empty or where the first entry is zero
        if (books.isEmpty()) {
            return false;
        }

        int firstQuantity = books.values().iterator().next();
        return books.values().stream().allMatch(quantity -> quantity == firstQuantity);

    }

    public double calculateTotalPriceOfAllQuantityAreEqual(Basket basket) {

        int quantityPerBook = basket.getBasket().values().iterator().next();
        return quantityPerBook * BOOK_PRICE * 5 * (1 - DISCOUNT_25_PERCENT);
    }

    public boolean isUniqueBookSeriesPurchased(Basket basket) {

        return getNonZeroSeriesCount(basket) == 1;
    }

    public double calculateTotalPriceOfSingleSeriesPurchase(Basket basket) {

        return basket.getBasket().values().stream()
                .mapToInt(quantity -> quantity * BOOK_PRICE)
                .sum();
    }

    public boolean isTwoBookSeriesPurchased(Basket basket) {

        return getNonZeroSeriesCount(basket) == 2;
    }

    public double calculateTotalPriceOfTwoSeriesPurchase(Basket basket) {
        int totalQuantity = basket.getBasket().values().stream().mapToInt(Integer::intValue).sum();
        int pairs = totalQuantity / 2;
        int remainingBooks = totalQuantity % 2;

        double totalPrice = pairs * (2 * BOOK_PRICE) * (1 - DISCOUNT_5_PERCENT);
        totalPrice += remainingBooks * BOOK_PRICE;

        return totalPrice;
    }

    public boolean isThreeBookSeriesPurchased(Basket basket) {

        return getNonZeroSeriesCount(basket) == 3;
    }

    public double calculateTotalPriceOfThreeSeriesPurchase(Basket basket) {

        int totalQuantity = basket.getBasket().values().stream().mapToInt(Integer::intValue).sum();
        int pairs = totalQuantity / 3;
        int remainingBooks = totalQuantity % 3;

        double totalPrice = pairs * (3 * BOOK_PRICE) * (1 - DISCOUNT_10_PERCENT);
        totalPrice += remainingBooks * BOOK_PRICE;

        return totalPrice;
    }

    private int getNonZeroSeriesCount(Basket basket) {
        return (int) basket.getBasket().values().stream().filter(quantity -> quantity != 0).count();
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
