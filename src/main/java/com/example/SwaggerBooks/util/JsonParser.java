package com.example.SwaggerBooks.util;

import com.example.SwaggerBooks.model.Book;
import com.example.SwaggerBooks.model.Item;
import com.example.SwaggerBooks.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@PropertySource("classpath:application.properties")
public class JsonParser {

    @Value("${parser.source}")
    private String source;
    @Value("${parser.url}")
    private String url;
    @Value("${parser.file}")
    private String file;

    //private final String url = "https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40";
    private final List<Book> bookList = new ArrayList<>();

    @PostConstruct
    public void parseJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
        switch (source.toLowerCase()) {
            case "file": {
                System.out.println("using file");
                response = mapper.readValue(new FileReader(file), Response.class);
                break;
            }
            case "url": {
                System.out.println("using url");
                response = mapper.readValue(new URL(url), Response.class);
                break;
            }
            default: {
                throw new IOException("wrong source");
            }
        }
        response.getItems().forEach(item -> bookList.add(new Book(item)));
    }

    public List<Book> getBookList() {
        return bookList;
    }
}
