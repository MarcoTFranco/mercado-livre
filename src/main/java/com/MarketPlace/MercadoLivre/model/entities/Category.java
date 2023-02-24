package com.MarketPlace.MercadoLivre.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @ManyToOne
    private Category categoryMother;

    @Deprecated
    public Category() {
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public Category getCategoryMother() {
        return categoryMother;
    }

    public void setCategoryMother(Category categoryMother) {
        this.categoryMother = categoryMother;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + categoryMother +
                '}';
    }
}
