package com.kata.developmentbooks;

import com.kata.developmentbooks.models.Basket;
import com.kata.developmentbooks.models.Book;

import java.util.*;

public class BookStore {

    private Basket basket = new Basket();

    public void addQuantityOfBookToBasket(Book book, int quantity) {
        basket.addToBasket(book, quantity);
    }

    public boolean isAllQuantityAreEqual(Basket basket) {

        Map<Book, Integer> books = basket.getBasket();

        // Check if the Map is empty or where the first entry is zero
        if (books.isEmpty() || books.values().iterator().next() == 0) {
            return false;
        }

        int firstElement = books.values().iterator().next();

        // Check if all elements match the first element
        for (Map.Entry<Book, Integer> entry : books.entrySet()) {
            if (entry.getValue()!= firstElement) {
                return false;
            }
        }
        return true;

    }

    public double calculateTotalPriceOfAllQuantityAreEqual(Basket basket) {

        int bookPrice = 50;
        double discount = 0.25;
        int quantity = basket.getBasket().values().iterator().next();
        return  quantity * bookPrice * 5 * (1 - discount);
    }

    public boolean isUniqueBookSeriesPurchased(Basket basket) {

        Map<Book, Integer> books = basket.getBasket();
        int nonZeroCount = 0;

        for (Integer value : books.values()) {
            if (value != null && value != 0) {
                nonZeroCount++;
                if (nonZeroCount > 1) {
                    return false; // More than one non-zero value
                }
            }
        }

        // Return true if exactly one non-zero value was found
        return nonZeroCount == 1;
    }

    public double calculateTotalPriceOfSingleSeriesPurchase(Basket basket) {
        Map<Book, Integer> books = basket.getBasket();
        double totalPrice = 0.0;
        int bookPrice = 50;

        for (Integer value : books.values()) {
            totalPrice += value * bookPrice;

        }
        return totalPrice;
    }

    public boolean isTowBookSeriesPurchased(Basket basket) {

        Map<Book, Integer> books = basket.getBasket();
        int nonZeroCount = 0;

        for (Integer value : books.values()) {
            if (value != null && value != 0) {
                nonZeroCount++;
                if (nonZeroCount > 2) {
                    return false; // More than one non-zero value
                }
            }
        }

        // Return true if exactly one non-zero value was found
        return nonZeroCount == 2;
    }

    public double calculateTotalPriceOfTowSeriesPurchase(Basket basket) {
        Map<Book, Integer> books = basket.getBasket();
        double totalPrice = 0.0;
        int bookPrice = 50;
        int totalQuantity = 0;
        double discountMultiplier = 1 - 0.05;

        for (Integer value : books.values()) {
            totalQuantity += value;
        }


        int pairs = totalQuantity / 2;          // Number of pairs eligible for discount
        int remainingBooks = totalQuantity % 2; // Books without discount

        // Calculate the price for the pairs with the discount
        totalPrice += pairs * (2 * bookPrice) * discountMultiplier;

        // Add the price of any remaining books without discount
        totalPrice += remainingBooks * bookPrice;

        return totalPrice;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
