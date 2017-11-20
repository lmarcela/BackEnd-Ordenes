package com.marcela.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marcela.model.OrderDetail;

/**
 * Created by marcela
 */
@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail,Integer>{

}