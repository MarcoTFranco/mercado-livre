package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.model.entities.NewUser;
import com.MarketPlace.MercadoLivre.model.request.NewUserRequest;
import com.MarketPlace.MercadoLivre.service.NewUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private NewUserService service;

    @PostMapping(value = "/users")
    public String createUser (@RequestBody @Valid NewUserRequest request) {
        NewUser user = request.toModel();
        service.createUser(user);
        return user.toString();
    }

}
