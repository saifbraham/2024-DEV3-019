package com.kata.developmentbooks;

import com.kata.developmentbooks.models.Basket;
import com.kata.developmentbooks.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookStoreTest {

    @Autowired
    private BookStore store;

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;

    @BeforeEach
    void setupBooks() {
        book1 = new Book("Clean Code", "Robert Martin", 2008);
        book2 = new Book("The Clean Coder", "Robert Martin", 2011);
        book3 = new Book("Clean Architecture", "Robert Martin", 2017);
        book4 = new Book("Test Driven Development by Example", "Kent Beck", 2003);
        book5 = new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);
    }

    private void addBooksToStore(Basket basket, int... quantities) {
        basket.addToBasket(book1, quantities[0]);
        basket.addToBasket(book2, quantities[1]);
        basket.addToBasket(book3, quantities[2]);
        basket.addToBasket(book4, quantities[3]);
        basket.addToBasket(book5, quantities[4]);
    }

    @Test
    @Order(1)
    @DisplayName("Create basket with 5 books and corresponding quantities")
    void testCreateBasketOfFiveBooksWithQuantityToBuy(){

        Basket myBasket = store.getBasket();
        myBasket.clear();
        addBooksToStore(myBasket, 2, 2, 2, 1, 1);

        // Assert: Check that the quantity of book in the basket matches the expected value
        assertEquals(5, myBasket.getBasket().size());
        assertEquals(2, myBasket.getBasket().getOrDefault(book1,0));
        assertEquals(2, myBasket.getBasket().getOrDefault(book2,0));
        assertEquals(2, myBasket.getBasket().getOrDefault(book3,0));
        assertEquals(1, myBasket.getBasket().getOrDefault(book4,0));
        assertEquals(1, myBasket.getBasket().getOrDefault(book5,0));
    }

    @Test
    @Order(2)
    @DisplayName("Use case 1 - Verify if the quantities are equal across each book series")
    void testIfAllQuantityOfBooksAreEqual(){

        Basket basket = store.getBasket();


        basket.clear();
        addBooksToStore(basket, 0, 2, 2, 2, 2);
        assertEquals(4, store.countEqualQuantities(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 2, 2, 2, 1);
        assertEquals(0, store.countEqualQuantities(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 2, 2, 2, 2, 2);
        assertEquals(5, store.countEqualQuantities(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 1, 1, 1, 1, 2);
        assertEquals(0, store.countEqualQuantities(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 0, 0, 1, 1);
        assertEquals(2, store.countEqualQuantities(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 0, 0, 1, 0);
        assertEquals(0, store.countEqualQuantities(basket.getQuantities()));


    }

    @Test
    @Order(3)
    @DisplayName("Compute the total price for equal quantities of book series, applying discount")
    void testCalculateTotalPriceOfAllQuantityAreEqual(){

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 2, 2, 2, 2, 2);
        assertEquals(375, store.calculateTotalPriceOfAllQuantityAreEqual(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 2, 2, 2, 2);
        assertEquals(320, store.calculateTotalPriceOfAllQuantityAreEqual(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 0, 2, 2, 2);
        assertEquals(270, store.calculateTotalPriceOfAllQuantityAreEqual(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 0, 0, 3, 3);
        assertEquals(285, store.calculateTotalPriceOfAllQuantityAreEqual(basket.getQuantities()));

    }


    @Test
    @Order(4)
    @DisplayName("Check if only a single book series is being purchased")
    void testIfSingleBookIsPurchased() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 0, 0, 0, 0, 2);
        assertTrue(store.isUniqueBookSeriesPurchased(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 3, 0, 0, 2);
        assertFalse(store.isUniqueBookSeriesPurchased(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 3, 1, 1, 0);
        assertFalse(store.isUniqueBookSeriesPurchased(basket.getQuantities()));
    }

    @Test
    @Order(5)
    @DisplayName("Calculate total price without discount for a single book series purchase")
    void testCalculateTotalPriceSingleSeriesPurchase() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 0, 0, 3, 0, 0);
        assertEquals(150, store.calculateTotalPriceOfSingleSeriesPurchase(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 0, 0, 0, 4);
        assertEquals(200, store.calculateTotalPriceOfSingleSeriesPurchase(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 1, 0, 0, 0);
        assertEquals(50, store.calculateTotalPriceOfSingleSeriesPurchase(basket.getQuantities()));

    }

    @Test
    @Order(6)
    @DisplayName("Check if only two different book series is being purchased")
    void testIfTwoDifferentBookIsPurchased() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 1, 0, 0, 0, 2);
        assertTrue(store.isTwoBookSeriesPurchased(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 3, 0, 1, 0);
        assertTrue(store.isTwoBookSeriesPurchased(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 0, 4, 1, 2);
        assertFalse(store.isTwoBookSeriesPurchased(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 1, 0, 0, 4);
        assertTrue(store.isTwoBookSeriesPurchased(basket.getQuantities()));

    }

    @Test
    @Order(7)
    @DisplayName("Calculate total price with 5% discount for a two book series purchase")
    void testCalculateTotalPriceTwoSeriesPurchase() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 0, 0, 2, 5, 0);
        double price = store.calculateTotalPriceOfTwoSeriesPurchase(basket.getQuantities());
        assertEquals(340, price);

    }

    @Test
    @Order(8)
    @DisplayName("Check if only three different book series is being purchased")
    void testIfThreeDifferentBookIsPurchased() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 0, 1, 1, 2, 0);
        assertTrue(store.isThreeBookSeriesPurchased(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 3, 1, 2, 1);
        assertFalse(store.isThreeBookSeriesPurchased(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 2, 2, 0, 3, 0);
        assertTrue(store.isThreeBookSeriesPurchased(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 0, 1, 2, 2);
        assertTrue(store.isThreeBookSeriesPurchased(basket.getQuantities()));

    }

    @Test
    @Order(9)
    @DisplayName("Calculate total price with 10% discount for a three book series purchase")
    void testCalculateTotalPriceThreeSeriesPurchase() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 2, 0, 0, 1, 3);

        double price = store.calculateTotalPriceOfThreeSeriesPurchase(basket.getQuantities());
        assertEquals(270, price);

    }




}