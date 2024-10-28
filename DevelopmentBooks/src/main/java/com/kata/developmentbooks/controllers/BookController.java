package com.kata.developmentbooks.controllers;

import com.kata.developmentbooks.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {


    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Update to a GET method with a path parameter
    @GetMapping("/calculatePrice/{bookQuantities}")
    public double calculatePrice(@PathVariable String bookQuantities) {
        Map<String, Integer> quantitiesMap = parseQuantities(bookQuantities);
        List<Integer> quantities = mapToQuantitiesList(quantitiesMap);
        return bookService.calculatePricelessBySetsOfDifferentUniqueBookCounts(quantities);
    }

    private List<Integer> mapToQuantitiesList(Map<String, Integer> bookQuantities) {
        // Map quantities to a list in the order of the books
        return List.of(
                bookQuantities.getOrDefault("CleanCode", 0),
                bookQuantities.getOrDefault("TheCleanCoder", 0),
                bookQuantities.getOrDefault("CleanArchitecture", 0),
                bookQuantities.getOrDefault("TestDrivenDevelopmentbyExample", 0),
                bookQuantities.getOrDefault("WorkingEffectivelyWithLegacyCode", 0)
        );
    }

    // Method to parse the string and create a Map<String, Integer>
    private Map<String, Integer> parseQuantities(String bookQuantities) {
        return Arrays.stream(bookQuantities.split(","))
                .map(entry -> entry.split("="))
                .filter(pair -> pair.length == 2)
                .collect(Collectors.toMap(
                        pair -> pair[0].trim(),
                        pair -> Integer.parseInt(pair[1].trim())
                ));
    }
}
