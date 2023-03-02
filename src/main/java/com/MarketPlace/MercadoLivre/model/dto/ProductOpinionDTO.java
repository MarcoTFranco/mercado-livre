package com.MarketPlace.MercadoLivre.model.dto;

import com.MarketPlace.MercadoLivre.model.entities.ProductOpinion;

public class ProductOpinionDTO {

    private String title;

    private String description;

    private Integer note;

    public ProductOpinionDTO (ProductOpinion opinion) {
        this.title = opinion.getTitle();
        this.description = opinion.getDescription();
        this.note = opinion.getNote();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNote() {
        return note;
    }
}
