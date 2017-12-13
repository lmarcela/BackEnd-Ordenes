package com.marcela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcela.model.Customer;

/**
 * Created by marcela
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}