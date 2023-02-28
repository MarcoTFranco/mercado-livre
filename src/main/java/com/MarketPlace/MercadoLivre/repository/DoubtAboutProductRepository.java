package com.MarketPlace.MercadoLivre.repository;

import com.MarketPlace.MercadoLivre.model.entities.DoubtAboutProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoubtAboutProductRepository extends JpaRepository<DoubtAboutProduct, Long> {
}
