package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.UploaderFake;
import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.model.request.ImageRequest;
import com.MarketPlace.MercadoLivre.model.request.ProductRequest;
import com.MarketPlace.MercadoLivre.service.ProductService;
import com.MarketPlace.MercadoLivre.service.validator.ProhibitCharacteristicWithEqualNameValidator;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductService service;
    @Autowired
    private UploaderFake uploaderFake;

    @InitBinder(value = "productRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProhibitCharacteristicWithEqualNameValidator());
    }

    @RequestMapping("/products")
    public String createProduct(@RequestBody @Valid ProductRequest request) {
        // --- User logado
        User owner = service.findByEmailUser("user@gmail.com");

        Product product = request.toModel(entityManager, owner);
        service.createProduct(product);
        return product.toString();
    }

    @RequestMapping("/products/{id}/images")
    public String addImages(@PathVariable Long id, @Valid ImageRequest request) {
        Set<String> links = uploaderFake.send(request.getImages());
        Product product = entityManager.find(Product.class, id);
        product.associatesImages(links);
        service.saveProduct(product);
        return product.toString();
    }

}
