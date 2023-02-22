package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.Category;
import com.MarketPlace.MercadoLivre.service.annotations.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.util.Assert;

public class CategoryRequest {
    @NotBlank
    @UniqueValue(fieldName = "name", domainClass = Category.class)
    private String name;

    @Positive
    private Long idCategoryMother;

    @Deprecated
    public CategoryRequest() {
    }

    public CategoryRequest(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdCategoryMother() {
        return idCategoryMother;
    }

    public void setIdCategoryMother(Long idCategoryMother) {
        this.idCategoryMother = idCategoryMother;
    }

    public Category toModel(EntityManager manager) {
        Category category = new Category(name);
        if (idCategoryMother != null) {
            Assert.notNull(idCategoryMother, "O id da categoria mae deve ser valido");
            Category categoryMother = manager.find(Category.class, idCategoryMother);
            category.setCategoryMother(categoryMother);
        }
        return category;
    }
}
