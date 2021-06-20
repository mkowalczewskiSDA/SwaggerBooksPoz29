package com.example.SwaggerBooks.util;

import com.example.SwaggerBooks.model.Item;
import com.example.SwaggerBooks.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Component
public class JsonParser {

    private String url = "https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40";

    @PostConstruct
    public void parseJson() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Response response = mapper.readValue(new URL(url), Response.class);
        System.out.println("test");
    }

}
