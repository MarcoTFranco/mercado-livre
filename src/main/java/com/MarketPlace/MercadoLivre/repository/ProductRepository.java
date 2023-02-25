package com.MarketPlace.MercadoLivre.repository;

import com.MarketPlace.MercadoLivre.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
