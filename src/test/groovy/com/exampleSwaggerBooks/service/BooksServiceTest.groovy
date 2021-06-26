package com.exampleSwaggerBooks.service

import com.example.SwaggerBooks.service.BooksService
import com.example.SwaggerBooks.util.JsonParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class BooksServiceTest extends Specification {

    @Autowired
    BooksService booksService
    JsonParser jsonParser = Mock()

}
