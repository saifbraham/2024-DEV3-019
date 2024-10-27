package com.kata.developmentbooks.controllers;

import com.kata.developmentbooks.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/calculatePrice")
    public double calculatePrice(@RequestBody BookQuantityRequest request) {
        List<Integer> quantities = mapToQuantitiesList(request.getBookQuantities());
        return bookService.calculatePricelessBySetsOfDifferentUniqueBookCounts(quantities);
    }

    private List<Integer> mapToQuantitiesList(Map<String, Integer> bookQuantities) {

        // Map quantities to a list in the order of the books above
        return List.of(
                bookQuantities.getOrDefault("Clean Code", 0),
                bookQuantities.getOrDefault("The Clean Coder", 0),
                bookQuantities.getOrDefault("Clean Architecture", 0),
                bookQuantities.getOrDefault("Test Driven Development by Example", 0),
                bookQuantities.getOrDefault("Working Effectively With Legacy Code", 0)
        );
    }
}
