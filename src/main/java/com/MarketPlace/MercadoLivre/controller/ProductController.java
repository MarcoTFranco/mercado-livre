package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.model.request.ProductRequest;
import com.MarketPlace.MercadoLivre.repository.UserRepository;
import com.MarketPlace.MercadoLivre.service.ProductService;
import com.MarketPlace.MercadoLivre.service.validator.ProhibitCharacteristicWithEqualNameValidator;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService service;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProhibitCharacteristicWithEqualNameValidator());
    }

    @RequestMapping("/products")
    public String createProduct(@RequestBody @Valid ProductRequest request) {
        // --- User logado
        User owner = userRepository.findByEmail("user@gmail.com").get();

        Product product = request.toModel(entityManager, owner);
        service.createProduct(product);
        return product.toString();
    }

}
