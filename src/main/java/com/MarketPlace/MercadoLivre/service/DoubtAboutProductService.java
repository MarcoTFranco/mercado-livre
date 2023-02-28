package com.MarketPlace.MercadoLivre.service;

import com.MarketPlace.MercadoLivre.model.entities.DoubtAboutProduct;
import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.model.util.Mailer;
import com.MarketPlace.MercadoLivre.repository.DoubtAboutProductRepository;
import com.MarketPlace.MercadoLivre.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoubtAboutProductService {

    @Autowired
    private DoubtAboutProductRepository doubtAboutProductRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager manager;
    @Autowired
    private Mailer mailer;

    public void insert(DoubtAboutProduct doubtAboutProduct) {
        doubtAboutProductRepository.save(doubtAboutProduct);
    }

    public User findByEmailUser(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public Product findByIdProduct(Long id) {
        return manager.find(Product.class, id);
    }

    public void newDoubt(DoubtAboutProduct doubtAboutProduct) {
        mailer.send("<html>...</html>",
                "Nova pergunta...",
                doubtAboutProduct.getUserDoubt().getEmail(),
                "mercadofake@email.com",
                doubtAboutProduct.getProduct().getOwner().getEmail());
    }
}
