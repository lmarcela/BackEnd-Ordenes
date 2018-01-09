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

import com.fasterxml.jackson.databind.JsonNode;
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
		// Order[] order = new ObjectMapper().readValue(jsonResponse, Order[].class);
		// Assert.assertNotNull(order);
		System.out.println("Exito en getOrders con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void getOrdersCustomer() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/order/customer/" + 1).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// Order order = new ObjectMapper().readValue(jsonResponse, Order.class);
		// Assert.assertNotNull(order);
		// Assert.assertFalse(order.getCustomerName().isEmpty());
		System.out.println("Exito en getOrdersCustomer con los datos: \n " + JsonPath.parse(jsonResponse).json());
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

		ObjectMapper objectMapper = new ObjectMapper();
		;
		JsonNode jsonNode = objectMapper.readTree(jsonResponse);
		String customerName = jsonNode.get("customerName").asText();

		Assert.assertFalse(customerName.isEmpty());

		System.out.println("Exito en createOrder con los datos: \n " + JsonPath.parse(jsonResponse).json()
				+ " -customer: " + customerName);

	}
}
