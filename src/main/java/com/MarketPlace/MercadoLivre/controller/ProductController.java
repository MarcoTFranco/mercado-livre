package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.controller.exceptions.ProductNotBelongUserException;
import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.model.request.ImageRequest;
import com.MarketPlace.MercadoLivre.model.request.OpinionRequest;
import com.MarketPlace.MercadoLivre.model.request.ProductRequest;
import com.MarketPlace.MercadoLivre.model.util.Uploader;
import com.MarketPlace.MercadoLivre.service.ProductService;
import com.MarketPlace.MercadoLivre.service.security.auth.UserLogged;
import com.MarketPlace.MercadoLivre.service.validator.ProhibitCharacteristicWithEqualNameValidator;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
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
    private Uploader uploaderFake;

    @InitBinder(value = "productRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProhibitCharacteristicWithEqualNameValidator());
    }

    @PostMapping("/products")
    public String createProduct(@RequestBody @Valid ProductRequest request,
                                @AuthenticationPrincipal UserLogged userLogged) {
        // --- User logado
        User owner = service.findByEmailUser(userLogged.getEmail());

        Product product = request.toModel(entityManager, owner);
        service.insert(product);
        return product.toString();
    }

    @PostMapping("/products/{id}/images")
    public String addImages(@PathVariable Long id,
                            @Valid ImageRequest request,
                            @AuthenticationPrincipal UserLogged userLogged) {
        // --- User logado
        User owner = service.findByEmailUser(userLogged.getEmail());
        Product product = entityManager.find(Product.class, id);

        if (!product.belongsUser(owner)) {
            throw new ProductNotBelongUserException("Não tente adicionar imagens a produto que não é seu");
        }

        Set<String> links = uploaderFake.upload(request.getImages());
        product.associatesImages(links);
        service.update(product);
        return product.toString();
    }

    @PostMapping("/products/{id}/opinions")
    public String addOpinions(@PathVariable Long id,
                              @RequestBody @Valid OpinionRequest request,
                              @AuthenticationPrincipal UserLogged userLogged) {
        User userOpinion = service.findByEmailUser(userLogged.getEmail());
        Product product = entityManager.find(Product.class, id);

        Assert.isTrue(product != null, "Produto não existe!");

        product.associatesOpinions(request, userOpinion);
        service.update(product);
        return product.toString();
    }
}
