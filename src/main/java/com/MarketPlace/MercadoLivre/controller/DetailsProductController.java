package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.model.dto.ProductDTO;
import com.MarketPlace.MercadoLivre.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = new ProductDTO(service.findById(id));
        return ResponseEntity.ok(productDTO);
    }

}
