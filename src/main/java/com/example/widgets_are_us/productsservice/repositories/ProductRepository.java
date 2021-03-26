package com.example.widgets_are_us.productsservice.repositories;

import com.example.widgets_are_us.productsservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Optional<Product> findByProductId(Long id);

    public void deleteByProductId(Long id);
}
