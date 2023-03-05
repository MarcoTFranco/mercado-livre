package com.MarketPlace.MercadoLivre.service;

import com.MarketPlace.MercadoLivre.model.entities.Payment;
import com.MarketPlace.MercadoLivre.model.util.Mailer;
import com.MarketPlace.MercadoLivre.repository.PaymentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    private Mailer mailer;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PaymentRepository paymentRepository;

    public void insert(Payment payment) {
        paymentRepository.save(payment);
    }

    public <T> T find(Class<T> classe, Long id) {
        return entityManager.find(classe, id);
    }

    public void processTaxNote(Payment payment) {
        System.out.println("criando taxote ...");
    }

    public void processRanking(Payment payment) {
        System.out.println("criando ranking ...");
    }

    public void processEmail(Payment payment) {
        System.out.println("criando email ...");
    }

}
