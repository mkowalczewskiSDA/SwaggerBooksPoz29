package com.exampleSwaggerBooks.service

import com.example.SwaggerBooks.model.Book
import com.example.SwaggerBooks.service.BooksService
import com.example.SwaggerBooks.util.JsonParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class BooksServiceTest extends Specification {

    @Autowired
    BooksService service
    JsonParser jsonParser = Mock()
    List<Book> books = [new Book(
            description: "Test",
            publisher: "Test",
            title: "Test",
            publishedDate: "1999",
            isAvailableInPl: true,
            priceInPl: 10,
            authors: ["Test", "Test1"],
            categories: ["test"],
            industryCodes: ["isbn":"test"]
    )]

    def setup() {
        service.setJsonParser(jsonParser)
    }

    def "test"() {
        given:
        jsonParser.getBookList() >> books
        when:
        def booksByCat = service.findBooksByCategory("test")
        then:
        booksByCat.size() == 1
    }

}
