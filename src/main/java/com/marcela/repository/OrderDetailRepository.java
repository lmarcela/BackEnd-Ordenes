package com.marcela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcela.model.OrderDetail;

/**
 * Created by marcela
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}