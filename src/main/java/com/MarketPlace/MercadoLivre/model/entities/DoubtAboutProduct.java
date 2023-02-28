package com.MarketPlace.MercadoLivre.model.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
@Entity
@Table(name = "tb_doubtsProduct")
public class DoubtAboutProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @Valid
    @NotNull
    @ManyToOne
    private User userDoubt;
    @Valid
    @NotNull
    @ManyToOne
    private Product product;
    private LocalDate instantOfCreation;

    public DoubtAboutProduct(@NotBlank String title,
                             @Valid @NotNull User userDoubt,
                             @Valid @NotNull Product product) {
        this.title = title;
        this.userDoubt = userDoubt;
        this.product = product;
        this.instantOfCreation = LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public User getUserDoubt() {
        return userDoubt;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDate getInstantOfCreation() {
        return instantOfCreation;
    }

    @Override
    public String toString() {
        return "DoubtsAboutProduct{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userDoubt=" + userDoubt +
                ", product=" + product +
                ", instantOfCreation=" + instantOfCreation +
                '}';
    }
}
