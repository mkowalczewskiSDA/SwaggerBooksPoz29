package com.example.SwaggerBooks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
public class Book {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private Map<String, String> industryCodes;
    private List<String> categories;
    private boolean isAvailableInPl;
    private Double priceInPl;

    public Book(Item item) {
        this.title = item.getVolumeInfo().getTitle();
        this.authors = item.getVolumeInfo().getAuthors();
        this.publisher = item.getVolumeInfo().getPublisher();
        this.description = item.getVolumeInfo().getDescription();
        this.publishedDate = item.getVolumeInfo().getPublishedDate();
        industryCodes = new HashMap<>();
        item.getVolumeInfo()
                .getIndustryIdentifiers()
                .forEach(industryIdentifier -> industryCodes.put(
                        industryIdentifier.getType(),
                        industryIdentifier.getIdentifier()
                ));
        this.categories = item.getVolumeInfo().getCategories();
        //NOT_FOR_SALE=false reszta true
        this.isAvailableInPl =!item.getSaleInfo().getSaleability().equals("NOT_FOR_SALE");
        if (this.isAvailableInPl) {
            if (item.getSaleInfo().getSaleability().equals("FREE")) {
                this.priceInPl = 0.0;
            } else {
                this.priceInPl = item.getSaleInfo().getListPrice().getAmount();
            }
        }



    }

}
