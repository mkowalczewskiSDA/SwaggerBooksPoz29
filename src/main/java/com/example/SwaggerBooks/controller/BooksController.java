package com.example.SwaggerBooks.controller;

import com.example.SwaggerBooks.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    @GetMapping("/api/books/byIdentifier")
    public Book getBooksByIndustryIdentifier(@RequestParam String id) {

    }

}
