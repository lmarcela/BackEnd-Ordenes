package com.marcela;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderSpringOrdenesApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getOrders() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/orders").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("Exito en getOrders con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}
	
	@Test
	public void getOrder() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/order/1").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Assert.assertFalse(new ObjectMapper().readTree(jsonResponse).get("customerName").asText().isEmpty());
		System.out.println("Exito en getOrder (1) con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void getOrdersCustomer() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/order/customer/" + 1).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("Exito en getOrdersCustomer con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void getOrdersCustomerDates() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/order/customer/1/desde/2017-01-10/hasta/2018-02-05").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("Exito en getOrdersCustomerDates con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void getOrderCustomerLastMonth() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/order/customer/1/ultimoMes").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("Exito en getOrderCustomerLastMonth con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void getOrderNull() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/order/customer/" + 100).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Assert.assertTrue(jsonResponse.equals("[]"));
		System.out.println("Exito en getOrderNull:" + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void createOrder() throws Exception {
		String raw = "{ \"customerId\": 1, " + "\"deliveryAddress\": \"jgj 58686\", "
				+ "\"creationDate\": \"2018-01-09\", " + "\"productIds\": [2,1], \"productQuantitys\": [4,1] }";
		String jsonResponse = mockMvc
				.perform(post("/order/").content(raw).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Assert.assertFalse(new ObjectMapper().readTree(jsonResponse).get("customerName").asText().isEmpty());
		System.out.println("Exito en createOrder con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}
}
