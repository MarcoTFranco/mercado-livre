package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.model.dto.ProductDTO;
import com.MarketPlace.MercadoLivre.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DetailsProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return new ProductDTO(service.findById(id));
    }

}
