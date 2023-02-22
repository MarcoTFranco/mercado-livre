package com.MarketPlace.MercadoLivre.repository;

import com.MarketPlace.MercadoLivre.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
