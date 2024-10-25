package com.kata.developmentbooks;

import com.kata.developmentbooks.models.Basket;
import com.kata.developmentbooks.models.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookStoreTest {

    @Test
    @DisplayName("Create basket with 5 books and corresponding quantities")
    void testCreateBasketOfFiveBooksWithQuantityToBuy(){

        // Arrange: Create 5 books
        Book book1 = new Book("Clean Code", "Robert Martin", 2008);
        Book book2 = new Book("The Clean Coder", "Robert Martin", 2011);
        Book book3 = new Book("Clean Architecture", "Robert Martin", 2017);
        Book book4 = new Book("Test Driven Development by Example", "Kint Beck", 2003);
        Book book5 = new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);

        // Act: Create basket and add books to basket
        Basket myBasket = new Basket();
        myBasket.addToBasket(book1,2);
        myBasket.addToBasket(book2,2);
        myBasket.addToBasket(book3,2);
        myBasket.addToBasket(book4,1);
        myBasket.addToBasket(book5,1);

        // Assert: Check that the quantity of book in the basket matches the expected value
        assertEquals(5, myBasket.getBasket().size());
        assertEquals(2, myBasket.getBasket().getOrDefault(book1,0));
        assertEquals(2, myBasket.getBasket().getOrDefault(book2,0));
        assertEquals(2, myBasket.getBasket().getOrDefault(book3,0));
        assertEquals(1, myBasket.getBasket().getOrDefault(book4,0));
        assertEquals(1, myBasket.getBasket().getOrDefault(book5,0));
    }

    @Test
    @DisplayName("Verify if the quantities are equal across each book series")
    void testIfAllQuantityOfBooksAreEqual(){

        // Arrange: Set up a store and create a book basket with specified purchase quantities
        BookStore store = new BookStore();

        Book book1 = new Book("Clean Code", "Robert Martin", 2008);
        Book book2 = new Book("The Clean Coder", "Robert Martin", 2011);
        Book book3 = new Book("Clean Architecture", "Robert Martin", 2017);
        Book book4 = new Book("Test Driven Development by Example", "Kint Beck", 2003);
        Book book5 = new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);
        store.addQuantityOfBookToBasket(book1,2);
        store.addQuantityOfBookToBasket(book2,2);
        store.addQuantityOfBookToBasket(book3,2);
        store.addQuantityOfBookToBasket(book4,2);
        store.addQuantityOfBookToBasket(book5,2);



        // Act: Ensure each book quantity is identical
        boolean isQuantityEqual = store.isAllQuantityAreEqual(store.getBasket());

        // Assert: Confirm that each book has the same quantity
        assertTrue(isQuantityEqual);

    }

    @Test
    @DisplayName("Compute the total price for equal quantities of book series, applying a 25% discount")
    void testCalculateTotalPriceOfAllQuantityAreEqual(){

        // Arrange: Set up a store and create a book basket with specified purchase quantities
        BookStore store = new BookStore();

        Book book1 = new Book("Clean Code", "Robert Martin", 2008);
        Book book2 = new Book("The Clean Coder", "Robert Martin", 2011);
        Book book3 = new Book("Clean Architecture", "Robert Martin", 2017);
        Book book4 = new Book("Test Driven Development by Example", "Kint Beck", 2003);
        Book book5 = new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);
        store.addQuantityOfBookToBasket(book1,2);
        store.addQuantityOfBookToBasket(book2,2);
        store.addQuantityOfBookToBasket(book3,2);
        store.addQuantityOfBookToBasket(book4,2);
        store.addQuantityOfBookToBasket(book5,2);

        // Act: Calculate total price with 25% of discount
        double price = store.calculateTotalPriceOfAllQuantityAreEqual(store.getBasket());

        // Assert: Check that the total price matches the expected value
        assertEquals(375, price);

    }


    @Test
    @DisplayName("Check if only a single book series is being purchased")
    void testIfSingleBookIsPurchased() {

        // Arrange: Set up a store and create a book basket with specified purchase quantities
        BookStore store = new BookStore();

        Book book1 = new Book("Clean Code", "Robert Martin", 2008);
        Book book2 = new Book("The Clean Coder", "Robert Martin", 2011);
        Book book3 = new Book("Clean Architecture", "Robert Martin", 2017);
        Book book4 = new Book("Test Driven Development by Example", "Kint Beck", 2003);
        Book book5 = new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);
        store.addQuantityOfBookToBasket(book1, 0);
        store.addQuantityOfBookToBasket(book2, 0);
        store.addQuantityOfBookToBasket(book3, 0);
        store.addQuantityOfBookToBasket(book4, 0);
        store.addQuantityOfBookToBasket(book5, 2);

        // Act: Ensure that only one book series is purchased
        boolean isUniqueBookSeriesPurchased = store.isUniqueBookSeriesPurchased(store.getBasket());

        // Assert: Confirm that one book series is purchased
        assertTrue(isUniqueBookSeriesPurchased);

    }

    @Test
    @DisplayName("Calculate total price without discount for a single book series purchase")
    void testCalculateTotalPriceSingleSeriesPurchase() {

        // Arrange: Set up a store and create a book basket with specified purchase quantities
        BookStore store = new BookStore();

        Book book1 = new Book("Clean Code", "Robert Martin", 2008);
        Book book2 = new Book("The Clean Coder", "Robert Martin", 2011);
        Book book3 = new Book("Clean Architecture", "Robert Martin", 2017);
        Book book4 = new Book("Test Driven Development by Example", "Kint Beck", 2003);
        Book book5 = new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);
        store.addQuantityOfBookToBasket(book1,0);
        store.addQuantityOfBookToBasket(book2,0);
        store.addQuantityOfBookToBasket(book3,3);
        store.addQuantityOfBookToBasket(book4,0);
        store.addQuantityOfBookToBasket(book5,0);

        // Act: Calculate total price with 0% of discount
        double price = store.calculateTotalPriceOfSingleSeriesPurchase(store.getBasket());

        // Assert: Check that the total price matches the expected value
        assertEquals(150, price);

    }

    @Test
    @DisplayName("Check if only tow different book series is being purchased")
    void testIfTowDifferentBookIsPurchased() {

        // Arrange: Set up a store and create a book basket with specified purchase quantities
        BookStore store = new BookStore();

        Book book1 = new Book("Clean Code", "Robert Martin", 2008);
        Book book2 = new Book("The Clean Coder", "Robert Martin", 2011);
        Book book3 = new Book("Clean Architecture", "Robert Martin", 2017);
        Book book4 = new Book("Test Driven Development by Example", "Kint Beck", 2003);
        Book book5 = new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);
        store.addQuantityOfBookToBasket(book1, 1);
        store.addQuantityOfBookToBasket(book2, 0);
        store.addQuantityOfBookToBasket(book3, 0);
        store.addQuantityOfBookToBasket(book4, 0);
        store.addQuantityOfBookToBasket(book5, 2);

        // Act: Ensure that only tow book series is purchased
        boolean isTowBookSeriesPurchased = store.isTowBookSeriesPurchased(store.getBasket());

        // Assert: Confirm that tow book series is purchased
        assertTrue(isTowBookSeriesPurchased);

    }

    @Test
    @DisplayName("Calculate total price withe 5% discount for a tow book series purchase")
    void testCalculateTotalPriceTowSeriesPurchase() {

        // Arrange: Set up a store and create a book basket with specified purchase quantities
        BookStore store = new BookStore();

        Book book1 = new Book("Clean Code", "Robert Martin", 2008);
        Book book2 = new Book("The Clean Coder", "Robert Martin", 2011);
        Book book3 = new Book("Clean Architecture", "Robert Martin", 2017);
        Book book4 = new Book("Test Driven Development by Example", "Kint Beck", 2003);
        Book book5 = new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);
        store.addQuantityOfBookToBasket(book1,0);
        store.addQuantityOfBookToBasket(book2,0);
        store.addQuantityOfBookToBasket(book3,2);
        store.addQuantityOfBookToBasket(book4,0);
        store.addQuantityOfBookToBasket(book5,3);

        // Act: Calculate total price with 5% of discount
        double price = store.calculateTotalPriceOfTowSeriesPurchase(store.getBasket());

        // Assert: Check that the total price matches the expected value
            // TowDifferentBooks * 5% + TowDifferentBooks * 5% + OneBook * 50
            // (50 + 50) * 5% + (50 + 50) *5% + 1 * 50
        assertEquals(240, price);

    }


}