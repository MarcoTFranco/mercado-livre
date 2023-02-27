package com.MarketPlace.MercadoLivre.service;

import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.repository.ProductRepository;
import com.MarketPlace.MercadoLivre.repository.UserRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public User findByEmailUser(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
