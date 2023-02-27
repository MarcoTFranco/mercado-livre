package com.MarketPlace.MercadoLivre.model.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "tb_productImage")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Product product;
    @URL
    @NotNull
    private String link;

    @Deprecated
    public ProductImage() {
    }

    public ProductImage(@NotNull @Valid Product product, @URL String link) {
        this.link = link;
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
