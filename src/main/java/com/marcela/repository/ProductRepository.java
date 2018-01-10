package com.marcela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcela.model.Product;

/**
 * Created by marcela
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}