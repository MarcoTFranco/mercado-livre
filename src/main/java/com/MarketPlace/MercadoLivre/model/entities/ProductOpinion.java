package com.MarketPlace.MercadoLivre.model.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tb_productOpinion")
public class ProductOpinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @Length(max = 500)
    private String description;
    @Min(1)
    @Max(5)
    private Integer note;
    @ManyToOne
    @Valid
    @NotNull
    private Product product;
    @ManyToOne
    @Valid
    @NotNull
    private User owner;

    @Deprecated
    public ProductOpinion() {
    }

    public ProductOpinion(@NotBlank String title,
                          @Length(max = 500) String description,
                          @Min(1) @Max(5) Integer note,
                          @Valid @NotNull Product product,
                          @Valid @NotNull User owner) {
        this.title = title;
        this.description = description;
        this.note = note;
        this.product = product;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "ProductOpinion{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", note=" + note +
                ", user=" + owner +
                '}';
    }
}
