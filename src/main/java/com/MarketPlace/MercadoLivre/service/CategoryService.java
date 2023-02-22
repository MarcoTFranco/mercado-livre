package com.MarketPlace.MercadoLivre.service;

import com.MarketPlace.MercadoLivre.model.entities.Category;
import com.MarketPlace.MercadoLivre.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public void createCategory (Category category) {
        repository.save(category);
    }

}
