package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.DoubtAboutProduct;
import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;

public class DoubtRequest {

    @NotBlank
    private String title;

    @Deprecated
    public DoubtRequest() {
    }

    public DoubtRequest(@NotBlank String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DoubtAboutProduct toModel(@Valid @NotNull User userDoubt, @Valid @NotNull Product product) {
        Assert.isTrue(product != null, "Produto não existe!");
        Assert.isTrue(userDoubt != null, "Produto não existe!");

        return new DoubtAboutProduct(title, userDoubt, product);
    }
}
