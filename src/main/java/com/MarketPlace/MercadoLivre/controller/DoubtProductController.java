package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.model.entities.DoubtAboutProduct;
import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.model.request.DoubtRequest;
import com.MarketPlace.MercadoLivre.service.DoubtAboutProductService;
import com.MarketPlace.MercadoLivre.service.security.auth.UserLogged;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DoubtProductController {

    @Autowired
    private DoubtAboutProductService service;

    @PostMapping("/products/{id}/doubts")
    public String doubtsAboutTheProduct(@PathVariable Long id,
                                        @RequestBody @Valid DoubtRequest request,
                                        @AuthenticationPrincipal UserLogged userLogged) {
        User userDoubt = service.findByEmailUser(userLogged.getEmail());
        Product product = service.findByIdProduct(id);
        DoubtAboutProduct doubtAboutProduct = request.toModel(userDoubt, product);
        product.associatesDoubts(doubtAboutProduct);

        service.insert(doubtAboutProduct);
        service.newDoubt(doubtAboutProduct);
        return doubtAboutProduct.toString();
    }
}
