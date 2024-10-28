package com.kata.developmentbooks;

import com.kata.developmentbooks.controllers.BookController;
import com.kata.developmentbooks.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testCalculatePriceSingleBookSeries() throws Exception {
        // Set up the expected map and list for the service method
        String bookQuantities = "Clean Code=2";

        // Mock the bookService's response
        when(bookService.calculatePricelessBySetsOfDifferentUniqueBookCounts(List.of(2, 0, 0, 0, 0)))
                .thenReturn(100.0); // Mock response value

        // Perform the mock GET request and assert the result
        mockMvc.perform(get("/api/books/calculatePrice/{bookQuantities}", bookQuantities))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    public void testCalculatePriceFiveBookSeries() throws Exception {
        // Set up the expected map and list for the service method
        String bookQuantities = "Clean Code=2,The Clean Coder=2,Clean Architecture=2,Test Driven Development by Example=1,Working Effectively With Legacy Code=1";

        // Mock the bookService's response
        when(bookService.calculatePricelessBySetsOfDifferentUniqueBookCounts(List.of(2, 2, 2, 1, 1)))
                .thenReturn(320.0); // Mock response value

        // Perform the mock GET request and assert the result
        mockMvc.perform(get("/api/books/calculatePrice/{bookQuantities}", bookQuantities))
                .andExpect(status().isOk())
                .andExpect(content().string("320.0"));
    }

    @Test
    public void testCalculatePriceThreeBookSeries() throws Exception {
        // Set up the expected map and list for the service method
        String bookQuantities = "Clean Code=1,The Clean Coder=2,Clean Architecture=3";

        // Mock the bookService's response
        when(bookService.calculatePricelessBySetsOfDifferentUniqueBookCounts(List.of(1, 2, 3, 0, 0)))
                .thenReturn(280.0); // Mock response value

        // Perform the mock GET request and assert the result
        mockMvc.perform(get("/api/books/calculatePrice/{bookQuantities}", bookQuantities))
                .andExpect(status().isOk())
                .andExpect(content().string("280.0"));
    }
}