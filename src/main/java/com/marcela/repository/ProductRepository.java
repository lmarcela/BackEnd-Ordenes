package com.marcela.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marcela.model.Product;

/**
 * Created by marcela 
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}