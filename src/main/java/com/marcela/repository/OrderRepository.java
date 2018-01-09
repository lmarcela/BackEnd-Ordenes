package com.marcela.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.marcela.model.Order;

/**
 * Created by marcela
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
	//Listar ordenes de cliente
	@Async
	@Query("FROM Order o where o.customer.customerId = :id") 
	public List<Order> findByCustomer (@Param("id") int customerId);

	//Listar ordenes de cliente entre rango de fechas
	@Async
	@Query("FROM Order o where o.customer.customerId = :id and o.creationDate between :fechaInicio and :fechaFin") 
	public List<Order> findByCustomerDates (@Param("id") int customerId, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

	//Listar ordenes de cliente del ultimo mes
	@Async
	@Query("FROM Order o where o.customer.customerId = :id and o.creationDate between :ultimoMes and :fechaActual")  
	public List<Order> findByCustomerLastMonth (@Param("id") int customerId, @Param("fechaActual") Date fechaActual, @Param("ultimoMes") Date ultimoMes);

}