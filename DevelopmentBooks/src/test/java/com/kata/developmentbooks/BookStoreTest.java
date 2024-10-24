package com.kata.developmentbooks;

import com.kata.developmentbooks.models.Basket;
import com.kata.developmentbooks.models.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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



}