package com.esisba.productservice.repository;


import com.esisba.productservice.entitiy.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}