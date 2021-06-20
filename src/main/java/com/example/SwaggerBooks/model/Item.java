package com.example.SwaggerBooks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;
}
