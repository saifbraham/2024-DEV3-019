package com.kata.developmentbooks;

import com.kata.developmentbooks.controller.BookQuantityRequest;
import com.kata.developmentbooks.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCalculatePrice() throws Exception {
        // Define Book objects with details
        Book cleanCode = new Book("Clean Code", "Robert Martin", 2008);
        Book theCleanCoder = new Book("The Clean Coder", "Robert Martin", 2011);
        Book cleanArchitecture = new Book("Clean Architecture", "Robert Martin", 2017);
        Book tddByExample = new Book("Test Driven Development by Example", "Kent Beck", 2003);
        Book workingWithLegacyCode = new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004);

        // Define book quantities
        Map<Book, Integer> bookQuantities = Map.of(
                cleanCode, 2,
                theCleanCoder, 2,
                cleanArchitecture, 2,
                tddByExample, 1,
                workingWithLegacyCode, 1
        );

        BookQuantityRequest request = new BookQuantityRequest();
        request.setBookQuantities(bookQuantities);

        double expectedPrice = 320.0;

        // Perform the GET request
        mockMvc.perform(get("/api/books/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedPrice)));
    }
}
