package com.MarketPlace.MercadoLivre.model.dto;

import com.MarketPlace.MercadoLivre.model.entities.DoubtAboutProduct;

import java.time.LocalDate;

public class DoubtAboutProductDTO {

    private String title;
    private LocalDate instantOfCreation;

    public DoubtAboutProductDTO (DoubtAboutProduct doubts) {
        this.title = doubts.getTitle();
        this.instantOfCreation = doubts.getInstantOfCreation();
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getInstantOfCreation() {
        return instantOfCreation;
    }
}
