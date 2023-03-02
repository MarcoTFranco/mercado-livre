package com.MarketPlace.MercadoLivre.model.dto;

import com.MarketPlace.MercadoLivre.model.entities.ProductFeature;

public class ProductFeatureDTO {

    private final String name;
    private final String description;

    public ProductFeatureDTO(ProductFeature productFeature) {
        this.name = productFeature.getName();
        this.description = productFeature.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
