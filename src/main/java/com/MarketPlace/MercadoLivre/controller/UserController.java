package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.model.request.UserRequest;
import com.MarketPlace.MercadoLivre.service.UserService;
import com.MarketPlace.MercadoLivre.service.security.auth.AuthenticationRequest;
import com.MarketPlace.MercadoLivre.service.security.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/new")
    public String createUser(@RequestBody @Valid UserRequest request) {
        User user = request.toModel();
        service.insert(user);
        return user.toString();
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

}
