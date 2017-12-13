package com.marcela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcela.model.Customer;
import com.marcela.service.CustomerService;

@SpringBootApplication
public class SpringOrdenesApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringOrdenesApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		customerService.createCustomer(new Customer("Lina Marcela Malaver Gómez","marcela9409@gmail.com"));
		customerService.createCustomer(new Customer("Yessica Alexandra Castillo","yesscastillo@gmail.com"));
		customerService.createCustomer(new Customer("Jeisson Guerrero Quezada","jeisson@gmail.com"));
		customerService.createCustomer(new Customer("Jose Hernán Castañeda","hernan@live.com"));
		customerService.createCustomer(new Customer("Lina Maria Leon Blanco","lina@yahoo.es"));
	}

}
