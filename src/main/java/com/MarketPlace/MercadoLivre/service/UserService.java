package com.MarketPlace.MercadoLivre.service;

import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void createUser(User user) {
        repository.save(user);
    }

}
