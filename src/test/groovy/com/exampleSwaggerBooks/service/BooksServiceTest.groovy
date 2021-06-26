package com.exampleSwaggerBooks.service

import com.example.SwaggerBooks.model.Book
import com.example.SwaggerBooks.service.BooksService
import com.example.SwaggerBooks.util.JsonParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes = [BooksService.class, JsonParser.class])
@Unroll
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

    def "should return 2 first books from the list"() {
        given:
        jsonParser.getBookList() >> books
        when:
        def booksByCat = service.findBooksByCategory("test")
        then:
        booksByCat.size() == 2
        booksByCat.get(0).title == books.get(0).title
        booksByCat.get(1).title == books.get(1).title
    }

    def "should return books sorted by price"() {
        given:
        jsonParser.getBookList() >> books
        when:
        def booksByPrice = service.getBooksByPrice()
        then:
        booksByPrice.size() == 2
        booksByPrice.get(0).priceInPl == books.get(1).priceInPl
        booksByPrice.get(1).priceInPl == books.get(0).priceInPl
    }

    def "should return each book for each publisher for #publisher"() {
        given:
        jsonParser.getBookList() >> books
        when:
        def booksByPublisher = service.findBooksByPublisher(publisher)
        then:
        booksByPublisher.get(0).publishedDate == publishedDate
        where:
        publisher  | publishedDate
        "Test"     | "1999"
        "Test2"    | "1998"
        "Test3"    | "1997"
    }

}
