package com.kata.developmentbooks;

import com.kata.developmentbooks.models.Basket;
import com.kata.developmentbooks.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BookStore {

    private static final int BOOK_PRICE = 50;
    private static final double DISCOUNT_25_PERCENT = 0.25;
    private static final double DISCOUNT_20_PERCENT = 0.20;
    private static final double DISCOUNT_10_PERCENT = 0.10;
    private static final double DISCOUNT_5_PERCENT = 0.05;


    @Autowired
    private Basket basket;

    public int countEqualQuantities(List<Integer> quantities) {

        // Check if the List is empty
        if (quantities.isEmpty() || quantities.stream()
                .filter(quantity -> quantity > 0) // Only consider quantities greater than 0
                .count() == 1){
            return 0; // If there are no books or 1 book series, return 0
        }

        // Use a set to store distinct quantities greater than 0
        Set<Integer> distinctQuantities = quantities.stream()
                .filter(quantity -> quantity > 0) // Only consider quantities greater than 0
                .collect(Collectors.toSet());

        // Check if all distinct quantities are equal
        if (distinctQuantities.size() == 1) {
            // If there is exactly one distinct quantity, return its occurrence count
            Integer equalQuantity = distinctQuantities.iterator().next();
            return (int) quantities.stream()
                    .filter(quantity -> quantity.equals(equalQuantity)) // Count occurrences of the equal quantity
                    .count();
        } else {
            // If there are multiple distinct quantities or if they are not all equal, return 0
            return 0;
        }
    }

    public double calculateTotalPriceOfAllQuantityAreEqual(List<Integer> quantities) {

        // Use a list to store distinct quantities greater than 0
        List<Integer> distinctQuantities = quantities.stream()
                .filter(quantity -> quantity > 0) // Only consider quantities greater than 0
                .toList();

        int quantityPerBook = distinctQuantities.get(0);

        if(distinctQuantities.size() == 5)
            return quantityPerBook * BOOK_PRICE * 5 * (1 - DISCOUNT_25_PERCENT);
        if(distinctQuantities.size() == 4)
            return quantityPerBook * BOOK_PRICE * 4 * (1 - DISCOUNT_20_PERCENT);
        if(distinctQuantities.size() == 3)
            return quantityPerBook * BOOK_PRICE * 3 * (1 - DISCOUNT_10_PERCENT);
        if(distinctQuantities.size() == 2)
            return quantityPerBook * BOOK_PRICE * 2 * (1 - DISCOUNT_5_PERCENT);
        return 0;
    }

    public boolean isUniqueBookSeriesPurchased(List<Integer> quantities) {

        return getNonZeroSeriesCount(quantities) == 1;
    }

    public double calculateTotalPriceOfSingleSeriesPurchase(List<Integer> quantities) {

        return quantities.stream()
                .mapToInt(quantity -> quantity * BOOK_PRICE)
                .sum();
    }

    public boolean isTwoBookSeriesPurchased(List<Integer> quantities) {

        return getNonZeroSeriesCount(quantities) == 2;
    }

    public double calculateTotalPriceOfTwoSeriesPurchase(List<Integer> quantities) {

        // Use a list to store distinct quantities greater than 0
        List<Integer> distinctQuantities = quantities.stream()
                .filter(quantity -> quantity > 0) // Only consider quantities greater than 0
                .toList();

        // Find the minimum quantity, if any
        Optional<Integer> minQuantity = distinctQuantities.stream()
                .min(Integer::compareTo); // Get the minimum element

        // Calculate first price of couple book series with 5% discount
        double priceOfCouple = minQuantity.get() * BOOK_PRICE * 2 * (1 - DISCOUNT_5_PERCENT);

        // Subtract minQuantity from each element in distinctQuantities if minQuantity is present
        List<Integer> adjustedQuantities = minQuantity.map(min ->
                distinctQuantities.stream()
                        .map(quantity -> quantity - min) // Subtract min from each quantity
                        .toList()
        ).orElse(Collections.emptyList()); // Return an empty list if no minQuantity exists

        // Use a list to store distinct quantities greater than 0
        List<Integer> distinctQuantitiesOFTwo = adjustedQuantities.stream()
                .filter(quantity -> quantity > 0) // Only consider quantities greater than 0
                .toList();

        // Calculate price of rest
        double restPrice = distinctQuantitiesOFTwo.get(0) * BOOK_PRICE;

        // Return total price of couple book series with 5% discount
        return restPrice + priceOfCouple;

    }

    public boolean isThreeBookSeriesPurchased(List<Integer> quantities) {

        return getNonZeroSeriesCount(quantities) == 3;
    }

    public double calculateTotalPriceOfThreeSeriesPurchase(List<Integer> quantities) {

        int totalQuantity = quantities.stream().mapToInt(Integer::intValue).sum();
        int pairs = totalQuantity / 3;
        int remainingBooks = totalQuantity % 3;

        double totalPrice = pairs * (3 * BOOK_PRICE) * (1 - DISCOUNT_10_PERCENT);
        totalPrice += remainingBooks * BOOK_PRICE;

        return totalPrice;
    }

    private int getNonZeroSeriesCount(List<Integer> quantities) {
        return (int) quantities.stream().filter(quantity -> quantity != 0).count();
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
