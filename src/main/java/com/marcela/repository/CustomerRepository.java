package com.marcela.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marcela.model.Customer;

/**
 * Created by marcela
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}