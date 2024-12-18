package com.kata.developmentbooks;

import com.kata.developmentbooks.models.Basket;
import com.kata.developmentbooks.models.Book;
import com.kata.developmentbooks.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService store;

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
    @DisplayName("Calculate total price for a SINGLE book series purchase")
    void testCalculateTotalPriceOfSingleSeriesPurchase() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 0, 0, 2, 0, 0);
        assertEquals(100, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 3, 0, 0, 0, 0);
        assertEquals(150, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 0, 0, 1, 0);
        assertEquals(50, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 0, 0, 0, 0);
        assertEquals(0.0, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

    }


    @Test
    @Order(3)
    @DisplayName("Calculate total price with 5% discount for a two book series purchase")
    void testCalculateTotalPriceTwoSeriesPurchase() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 0, 0, 2, 5, 0);
        assertEquals(340, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 1, 0, 2, 0, 0);
        assertEquals(145, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 2, 2, 0, 0);
        assertEquals(190, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 1, 1, 0, 0);
        assertEquals(95, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

    }

    @Test
    @Order(4)
    @DisplayName("Calculate total price with discount for a maximum three different book series purchase")
    void testCalculateTotalPriceOfMaxThreeSeriesPurchase() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 1, 2, 3, 0, 0);
        assertEquals(280, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 1, 2, 0, 1);
        assertEquals(185, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 2, 2, 0, 2);
        assertEquals(270, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 0, 1, 1, 0, 1);
        assertEquals(135, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

    }

    @Test
    @Order(5)
    @DisplayName("Calculate priceless for max four book series purchase")
    void testCalculateTotalPriceOfFourSeriesPurchase() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 2, 2, 1, 1, 0);
        assertEquals(255, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 2, 2, 2, 1, 0);
        assertEquals(295, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 1, 1, 1, 1, 0);
        assertEquals(160, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 1, 2, 1, 1, 0);
        // Grouped by 2 : [1, 1] + [1, 1] + [1] = 5 BOOKS -> [50 * 2 * 5%] + [50 * 2 * 5%] + 50 = 240
        // Grouped by 3 : [1, 1, 1] + [1, 1] = 5 BOOKS -> [50 * 3 * 10%] + [50 * 2 * 5%] = 230
        // Grouped by 4 : [1, 1, 1, 1] + [1] = 5 BOOKS -> [50 * 4 * 20%] + 50 = 210 ->  Priceless
        assertEquals(210, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));


    }

    @Test
    @Order(6)
    @DisplayName("Calculate priceless for max five book series purchase")
    void testCalculateTotalPriceOfFiveSeriesPurchase() {

        Basket basket = store.getBasket();

        basket.clear();
        addBooksToStore(basket, 2, 2, 2, 1, 1);
        assertEquals(320, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 1, 1, 1, 1, 1);
        assertEquals(187.5, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

        basket.clear();
        addBooksToStore(basket, 1, 2, 1, 1, 1);
        assertEquals(237.5, store.calculatePricelessBySetsOfDifferentUniqueBookCounts(basket.getQuantities()));

    }

}