package com.MarketPlace.MercadoLivre.model.dto;

import com.MarketPlace.MercadoLivre.model.entities.ProductImage;

public class ProductImageDTO {

    private final String link;

    public ProductImageDTO (ProductImage image) {
        this.link = image.getLink();
    }

    public String getLink() {
        return link;
    }
}
