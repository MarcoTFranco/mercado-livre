package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.Feature;
import com.MarketPlace.MercadoLivre.model.entities.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FeaturesRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @Deprecated
    public FeaturesRequest() {
    }

    public FeaturesRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Feature toModel(@NotNull @Valid Product product) {
        return new Feature(name, description, product);
    }
}
