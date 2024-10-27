package com.kata.developmentbooks;

import com.kata.developmentbooks.models.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookStore {

    private static final int BOOK_PRICE = 50;
    private static final double DISCOUNT_25_PERCENT = 0.25;
    private static final double DISCOUNT_20_PERCENT = 0.20;
    private static final double DISCOUNT_10_PERCENT = 0.10;
    private static final double DISCOUNT_5_PERCENT = 0.05;


    Basket basket;

    @Autowired
    private BookStore(Basket basket){
        this.basket = basket;
    }


    public double calculateTotalPriceOfTwoSeriesPurchase(List<Integer> quantities) {

        // If all quantities are zero, no more books to purchase
        if (Collections.max(quantities) == 0) {
            return 0.0;
        }

        double totalPrice = 0.0;

        // Copy quantities to avoid modifying the original list
        List<Integer> remainingBooks = new ArrayList<>(quantities);

        while (Collections.max(remainingBooks) > 0) {
            // Count the number of unique books in this set
            int uniqueBooks = 0;
            for (int i = 0; i < remainingBooks.size(); i++) {
                if (remainingBooks.get(i) > 0) {
                    uniqueBooks++;
                    remainingBooks.set(i, remainingBooks.get(i) - 1);
                }
            }

            // Calculate price for this set of unique books
            double setPrice = uniqueBooks == 2?uniqueBooks * BOOK_PRICE * (1 - DISCOUNT_5_PERCENT):uniqueBooks * BOOK_PRICE ;
            totalPrice += setPrice;
        }

        return totalPrice;

    }


    public double calculateTotalPriceOfThreeSeriesPurchase(List<Integer> quantities) {

        // If all quantities are zero, no more books to purchase
        if (Collections.max(quantities) == 0) {
            return 0.0;
        }

        double totalPrice = 0.0;

        // Copy quantities to avoid modifying the original list
        List<Integer> remainingBooks = new ArrayList<>(quantities);

        while (Collections.max(remainingBooks) > 0) {
            // Count the number of unique books in this set
            int uniqueBooks = 0;
            for (int i = 0; i < remainingBooks.size(); i++) {
                if (remainingBooks.get(i) > 0) {
                    uniqueBooks++;
                    remainingBooks.set(i, remainingBooks.get(i) - 1);
                }
            }

            double setPrice = 0.0;

            // Calculate price for this set of unique books
            if(uniqueBooks == 3)
                setPrice = uniqueBooks * BOOK_PRICE * (1 - DISCOUNT_10_PERCENT);
            if(uniqueBooks == 2)
                setPrice = uniqueBooks * BOOK_PRICE * (1 - DISCOUNT_5_PERCENT);
            if(uniqueBooks == 1)
                setPrice = uniqueBooks * BOOK_PRICE;

            totalPrice += setPrice;
        }

        return totalPrice;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}

