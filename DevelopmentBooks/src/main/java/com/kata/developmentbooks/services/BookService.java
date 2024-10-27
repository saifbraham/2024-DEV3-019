package com.kata.developmentbooks.services;

import com.kata.developmentbooks.models.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    private static final int BOOK_PRICE = 50;
    private final Basket basket;

    @Autowired
    private BookService(Basket basket){
        this.basket = basket;
    }

    public double calculatePricelessBySetsOfDifferentUniqueBookCounts(List<Integer> quantities) {

        // Using a recursive helper function to find the minimum price
        return calculateMinPrice(quantities);
    }

    private double calculateMinPrice(List<Integer> quantities) {
        // If all quantities are zero, no more books to purchase
        if (Collections.max(quantities) == 0) {
            return 0.0;
        }

        double minPrice = Double.MAX_VALUE;

        // Try creating sets of different unique book counts (2, 3, 4, or 5 unique books)
        for (int uniqueBooks = 1; uniqueBooks <= 5; uniqueBooks++) {
            List<Integer> newQuantities = new ArrayList<>(quantities);
            int actualUniqueBooks = 0;

            // Decrease quantity for each unique book to form a set
            for (int i = 0; i < newQuantities.size() && actualUniqueBooks < uniqueBooks; i++) {
                if (newQuantities.get(i) > 0) {
                    newQuantities.set(i, newQuantities.get(i) - 1);
                    actualUniqueBooks++;
                }
            }

            // Only calculate if we formed the desired unique set
            if (actualUniqueBooks == uniqueBooks) {
                // Calculate discount based on the size of this unique set
                double discount = getDiscount(uniqueBooks);
                double setPrice = uniqueBooks * BOOK_PRICE * (1 - discount);

                // Recursive call to calculate the remaining books' price
                double priceForRemainingBooks = calculateMinPrice(newQuantities);

                // Calculate the total price for this configuration
                minPrice = Math.min(minPrice, setPrice + priceForRemainingBooks);
            }
        }

        return minPrice;
    }

    private double getDiscount(int uniqueBooks) {
        return switch (uniqueBooks) {
            case 2 -> 0.05; // 5% discount for 2 unique books
            case 3 -> 0.10; // 10% discount for 3 unique books
            case 4 -> 0.20; // 20% discount for 4 unique books
            case 5 -> 0.25; // 25% discount for 5 unique books
            default -> 0.0;
        };
    }

    public Basket getBasket() {
        return basket;
    }
}
