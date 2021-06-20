package com.example.SwaggerBooks.controller;

import com.example.SwaggerBooks.excepetion.NoBooksException;
import com.example.SwaggerBooks.model.Book;
import com.example.SwaggerBooks.service.BooksService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    BooksService booksService;

    @GetMapping("/api/books/byIdentifier")
    @ApiOperation("Gets books from our api, by various industry identifiers")
    public Book getBooksByIndustryIdentifier(@RequestParam @ApiParam(value = "Provide ISBN13 or ISBN10 or any other industry identifier") String id) {
        return booksService.findBookByIndustryIdentifier(id).orElseThrow(NoBooksException::new);
    }

    @GetMapping("/api/{category}/books")
    public List<Book> findByCategory(@PathVariable String category) {
        return booksService.findBooksByCategory(category);
    }

    @GetMapping("/api/publisher")
    public List<Book> findByPublisher(@RequestParam String publisher) {
        return booksService.findBooksByPublisher(publisher);
    }

    @GetMapping("/api/title")
    public List<Book> findByTitle(@RequestParam String title) {
        return booksService.findByTitleStartingWith(title);
    }

    @GetMapping("/api/price")
    public List<Book> getByPrice() {
        return booksService.getBooksByPrice();
    }

    @PutMapping("/api/new")
    public Book addBook(@RequestBody Book book) {
        System.out.println(book);
        booksService.add(book);
        return book;
    }

    @ExceptionHandler(NoBooksException.class)
    @GetMapping("/api/book/nonexisting")
    public String nonExisting() {
        return "Book not found";
    }

}
