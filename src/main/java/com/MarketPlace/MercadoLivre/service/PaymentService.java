package com.MarketPlace.MercadoLivre.service;

import com.MarketPlace.MercadoLivre.model.entities.Payment;
import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.model.util.Mailer;
import com.MarketPlace.MercadoLivre.repository.PaymentRepository;
import com.MarketPlace.MercadoLivre.repository.ProductRepository;
import com.MarketPlace.MercadoLivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private Mailer mailer;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public User findByEmailUser(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public Product findByIdProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void createPayment(Payment payment) {
        paymentRepository.save(payment);
    }
}
