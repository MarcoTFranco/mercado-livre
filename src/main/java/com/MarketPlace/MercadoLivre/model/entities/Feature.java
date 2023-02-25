package com.MarketPlace.MercadoLivre.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Embeddable
public class Feature {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Valid
    @ManyToOne
    private Product product;

    @Deprecated
    public Feature() {
    }

    public Feature(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return name.equals(feature.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
