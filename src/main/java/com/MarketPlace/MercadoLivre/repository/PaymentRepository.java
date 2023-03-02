package com.MarketPlace.MercadoLivre.repository;

import com.MarketPlace.MercadoLivre.model.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
