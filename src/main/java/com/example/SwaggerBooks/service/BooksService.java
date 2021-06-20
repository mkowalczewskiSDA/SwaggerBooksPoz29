package com.example.SwaggerBooks.service;

import com.example.SwaggerBooks.model.Book;
import com.example.SwaggerBooks.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BooksService {

    @Autowired
    JsonParser jsonParser;

    public Optional<Book> findBookByIndustryIdentifier(String id) {
        return jsonParser.getBookList()
                .stream()
                .filter(book -> book.getIndustryCodes().containsValue(id))
                .findAny();
    }

    public List<Book> findBooksByPublisher(String publisher) {
        return jsonParser.getBookList()
                .stream()
                .filter(book -> publisher.equals(book.getPublisher()))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByCategory(String category) {
        return jsonParser.getBookList()
                .stream()
                .filter(book -> {
                    if (book.getCategories() != null) {
                        return book.getCategories().contains(category);
                    } else {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByPrice() {
        return jsonParser.getBookList()
                .stream()
                .filter(Book::isAvailableInPl)
                .sorted(Comparator.comparing(Book::getPriceInPl).reversed())
                .collect(Collectors.toList());
    }

}
