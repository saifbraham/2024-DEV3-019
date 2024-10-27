package com.kata.developmentbooks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.developmentbooks.controllers.BookController;
import com.kata.developmentbooks.controllers.BookQuantityRequest;
import com.kata.developmentbooks.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCalculatePriceSingleBookSeries() throws Exception {
        // Prepare a mock BookQuantityRequest
        BookQuantityRequest request = new BookQuantityRequest();
        request.setBookQuantities(Map.of("Clean Code", 2)); // Set up quantities for test

        // Convert request to JSON
        String requestJson = objectMapper.writeValueAsString(request);

        // Mock the bookService's response
        when(bookService.calculatePricelessBySetsOfDifferentUniqueBookCounts(List.of(2, 0, 0, 0, 0)))
                .thenReturn(100.0); // Mock response value

        // Perform the mock request and assert the result
        mockMvc.perform(post("/api/books/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    public void testCalculatePriceFiveBookSeries() throws Exception {
        // Prepare a mock BookQuantityRequest
        BookQuantityRequest request = new BookQuantityRequest();
        request.setBookQuantities(Map.of(
                "Clean Code", 2,
                "The Clean Coder", 2,
                "Clean Architecture", 2,
                "Test Driven Development by Example", 1,
                "Working Effectively With Legacy Code", 1
        )); // Set up quantities for test

        // Convert request to JSON
        String requestJson = objectMapper.writeValueAsString(request);

        // Mock the bookService's response
        when(bookService.calculatePricelessBySetsOfDifferentUniqueBookCounts(List.of(2, 2, 2, 1, 1)))
                .thenReturn(320.0); // Mock response value

        // Perform the mock request and assert the result
        mockMvc.perform(post("/api/books/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("320.0"));
    }

    @Test
    public void testCalculatePriceThreeBookSeries() throws Exception {
        // Prepare a mock BookQuantityRequest
        BookQuantityRequest request = new BookQuantityRequest();
        request.setBookQuantities(Map.of(
                "Clean Code", 1,
                "The Clean Coder", 2,
                "Clean Architecture", 3
        )); // Set up quantities for test

        // Convert request to JSON
        String requestJson = objectMapper.writeValueAsString(request);

        // Mock the bookService's response
        when(bookService.calculatePricelessBySetsOfDifferentUniqueBookCounts(List.of(1, 2, 3, 0, 0)))
                .thenReturn(280.0); // Mock response value

        // Perform the mock request and assert the result
        mockMvc.perform(post("/api/books/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("280.0"));
    }
}