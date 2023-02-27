package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.Category;
import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductRequest {
    @NotBlank
    private String name;
    @Positive
    private BigDecimal value;
    @PositiveOrZero
    private Integer amountAvailable;
    @Size(max = 1000)
    private String description;
    @NotNull
    private Long categoryId;
    @Size(min = 3)
    @Valid
    private List<FeaturesRequest> features = new ArrayList<>();

    @Deprecated
    public ProductRequest() {
    }

    public ProductRequest(@NotBlank String name, @Positive BigDecimal value,
                          @PositiveOrZero Integer amountAvailable,
                          @Size(max = 1000) String description,
                          @NotNull Long categoryId,
                          @Size(min = 3) List<FeaturesRequest> features) {
        this.name = name;
        this.value = value;
        this.amountAvailable = amountAvailable;
        this.description = description;
        this.categoryId = categoryId;
        this.features = features;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public List<FeaturesRequest> getFeatures() {
        return features;
    }


    public Product toModel(EntityManager manager, User owner) {
        Category category = manager.find(Category.class, categoryId);
        Assert.isTrue(category != null, "Um produto deve ter uma categoria");

        return new Product(name, value, amountAvailable, description, category, owner, features);
    }

    public Set<String> haveCharacteristicEqual() {
        HashSet<String> equalNames = new HashSet<>();
        HashSet<String> results = new HashSet<>();

        for (FeaturesRequest feature : features) {
            String name = feature.getName();
            if (!equalNames.add(name)) {
                results.add(name);
            }
        }
        return results;
    }
}
