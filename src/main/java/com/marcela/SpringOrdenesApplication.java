package com.marcela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcela.model.Customer;
import com.marcela.service.CustomerService;
import com.marcela.model.Product;
import com.marcela.service.ProductService;

@SpringBootApplication
public class SpringOrdenesApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(SpringOrdenesApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		customerService.createCustomer(new Customer("Lina Marcela Malaver Gómez", "marcela9409@gmail.com"));
		customerService.createCustomer(new Customer("Yessica Alexandra Castillo", "yesscastillo@gmail.com"));
		customerService.createCustomer(new Customer("Jeisson Guerrero Quezada", "jeisson@gmail.com"));
		customerService.createCustomer(new Customer("Jose Hernán Castañeda", "hernan@live.com"));
		customerService.createCustomer(new Customer("Lina Maria Leon Blanco", "lina@yahoo.es"));
		productService.createProduct(new Product("Tablet LENOVO Yoga 3 8\"\"  N", 430000.00));
		productService.createProduct(new Product("Tablet HUAWEI T1-701W WiFi - G", 300000.00));
	}

}
