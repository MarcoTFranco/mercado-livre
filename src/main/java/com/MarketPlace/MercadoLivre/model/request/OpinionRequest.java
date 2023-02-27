package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.ProductOpinion;
import com.MarketPlace.MercadoLivre.model.entities.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class OpinionRequest {

    @NotBlank
    private String title;
    @Length(max = 500)
    private String description;
    @Min(1)
    @Max(5)
    private Integer note;

    @Deprecated
    public OpinionRequest() {
    }

    public OpinionRequest( @NotBlank String title,
                           @Length(max = 500) String description,
                           @Min(1) @Max(5) Integer note) {
        this.title = title;
        this.description = description;
        this.note = note;
    }

    public Integer getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public ProductOpinion toModel(Product product, User user) {
        return new ProductOpinion(title, description, note, product, user);
    }
}
