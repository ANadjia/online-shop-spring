package com.esisba.productservice.repository;

import com.esisba.productservice.entitiy.Category;
import com.esisba.productservice.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByCategory(Category category);

    List<Product> findProductsByProductName(String name);
}