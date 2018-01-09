package com.marcela;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcela.model.Customer;
import com.marcela.model.Order;
import com.marcela.model.OrderDetail;
import com.marcela.service.CustomerService;
import com.marcela.service.OrderDetailService;
import com.marcela.service.OrderService;
import com.marcela.model.Product;
import com.marcela.service.ProductService;

@SpringBootApplication
public class SpringOrdenesApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;

	public static void main(String[] args) {
		SpringApplication.run(SpringOrdenesApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Product product = new Product();
		product.setName("galletas A");
		product.setPrice(2000);
		productService.createProduct(product);
		Set<Product> array = new HashSet<Product>();
		Product product2 = new Product();
		product2.setName("galletas B");
		product2.setPrice(3000);
		productService.createProduct(product2);
		array.add(product);
		array.add(product2);
		Customer customer = new Customer();
		customer.setName("Mariela Ortiz");
		customer.setEmail("mariela@mail.com");
		customer.setProducts(array);
		customerService.createCustomer(customer);
		Order or = new Order();
		//or.setCustomer(customer);
		or.setCreationDate(Date.valueOf("2018-01-01"));
		or.setDeliveryAddress("cra 56");
		or.setTotal(6000);
		//System.out.println("Total"+or.getCustomerId());
		//orderService.save(or);
		
		orderService.createOrder(or);
		
		OrderDetail od = new OrderDetail();
		//od.setOrder(or);
		//od.setProduct(product);
		od.setPrice(2000);
		od.setProductDescription("galletas A");
		od.setQuantity(3);
		orderDetailService.save(od);
		od.setOrder(or);
		od.setProduct(product);
		orderDetailService.save(od);
		
		/*Set<OrderDetail> array2 = new HashSet<OrderDetail>();
		array2.add(od);
		or.setOrderDetails(array2);
		*/
		
		
		or.setCustomer(customer);
		orderService.createOrder(or);
		
		

		/*productService.createProduct(new Product("Tablet LENOVO Yoga 3 8\"\"  N", 430000.00));
		productService.createProduct(new Product("Tablet HUAWEI T1-701W WiFi - G", 300000.00));
		customerService.createCustomer(new Customer("Lina Marcela Malaver Gómez", "marcela9409@gmail.com"));
		customerService.createCustomer(new Customer("Yessica Alexandra Castillo", "yesscastillo@gmail.com"));
		customerService.createCustomer(new Customer("Jeisson Guerrero Quezada", "jeisson@gmail.com"));
		customerService.createCustomer(new Customer("Jose Hernán Castañeda", "hernan@live.com"));
		customerService.createCustomer(new Customer("Lina Maria Leon Blanco", "lina@yahoo.es"));
		*/
	}

}
