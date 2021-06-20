package com.example.SwaggerBooks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {

    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private Map<String, String> industryIdentifiers;
    private List<String> categories;


}
