package com.exampleSwaggerBooks.service

import com.example.SwaggerBooks.model.Book
import com.example.SwaggerBooks.service.BooksService
import com.example.SwaggerBooks.util.JsonParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = [BooksService.class, JsonParser.class])
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
    ), new Book(
            description: "Test2",
            publisher: "Test2",
            title: "Test2",
            publishedDate: "1998",
            isAvailableInPl: true,
            priceInPl: 30,
            authors: ["Test2", "Test3"],
            categories: ["test"],
            industryCodes: ["isbn":"test2"]
    ), new Book(
            description: "Test3",
            publisher: "Test3",
            title: "Test3",
            publishedDate: "1997",
            isAvailableInPl: false,
            priceInPl: null,
            authors: ["Test"],
            categories: ["test2"],
            industryCodes: ["isbn":"test3", "isbn2":"test4"]
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
