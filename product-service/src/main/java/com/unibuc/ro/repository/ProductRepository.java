package com.unibuc.ro.repository;

import com.unibuc.ro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteByName(String name);

    Optional<Product> findByName(String name);
}
