package com.example.SwaggerBooks.service;

import com.example.SwaggerBooks.model.Book;
import com.example.SwaggerBooks.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

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

}
