package com.example.SwaggerBooks.controller;

import com.example.SwaggerBooks.excepetion.NoBooksException;
import com.example.SwaggerBooks.model.Book;
import com.example.SwaggerBooks.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    @Autowired
    BooksService booksService;

    @GetMapping("/api/books/byIdentifier")
    public Book getBooksByIndustryIdentifier(@RequestParam String id) {
        return booksService.findBookByIndustryIdentifier(id).orElseThrow(NoBooksException::new);
    }

    @ExceptionHandler(NoBooksException.class)
    @GetMapping("/api/book/nonexisting")
    public String nonExisting() {
        return "Book not found";
    }

}
