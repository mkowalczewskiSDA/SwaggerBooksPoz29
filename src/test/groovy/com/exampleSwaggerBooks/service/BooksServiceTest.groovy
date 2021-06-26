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
    static List<Book> books = [new Book(
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
            title: "Pest2",
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
        given: "setting up values"
        jsonParser.getBookList() >> books
        when: "executing search by publisher #publisher"
        def booksByPublisher = service.findBooksByPublisher(publisher)
        then: "published date should be the same as provided in where"
        booksByPublisher.get(0).publishedDate == publishedDate
        where:
        publisher  | publishedDate
        "Test"     | "1999"
        "Test2"    | "1998"
        "Test3"    | "1997"
    }

    def "find books by title starting with"() {
        given:
        jsonParser.getBookList() >> books
        when:
        def bookByTitle = service.findByTitleStartingWith("Te")
        then:
        bookByTitle.size() == 2
        bookByTitle.get(0) == books.get(0)
        bookByTitle.get(1) == books.get(2)
    }

    def "should return book for each industry identifer"() {
        given: "setting up values"
        jsonParser.getBookList() >> books
        when: "executing search by publisher #publisher"
        def booksByPublisher = service.findBookByIndustryIdentifier(identirier)
        then: "published date should be the same as provided in where"
        booksByPublisher.get() == booksFromList
        where:
        identirier  | booksFromList
        "test"      | books.get(0)
        "test2"     | books.get(1)
        "test3"     | books.get(2)
        "test4"     | books.get(2)
    }

}
