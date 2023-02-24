package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.model.entities.Category;
import com.MarketPlace.MercadoLivre.model.request.CategoryRequest;
import com.MarketPlace.MercadoLivre.service.CategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/api/categories")
    public String createCategory (@RequestBody @Valid CategoryRequest request) {
        Category category = request.toModel(manager);
        service.createCategory(category);
        return category.toString();
    }

}
