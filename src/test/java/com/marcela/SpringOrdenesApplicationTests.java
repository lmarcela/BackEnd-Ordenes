package com.marcela;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.marcela.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringOrdenesApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getCustomers() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/customers/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Customer[] cus = new ObjectMapper().readValue(jsonResponse, Customer[].class);
		Assert.assertNotNull(cus);
		System.out.println(
				"Exito en getCustomers con los datos: " + cus.length + "\n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void getCustomer() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/customer/" + 1).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Customer cus = new ObjectMapper().readValue(jsonResponse, Customer.class);
		Assert.assertNotNull(cus);
		Assert.assertFalse(cus.getName().isEmpty());
		System.out.println("Exito en getCustomer con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void getCustomerNull() throws Exception {
		Assert.assertTrue(mockMvc
				.perform(get("/customer/" + 100).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString().isEmpty());
		System.out.println("Exito en getCustomerNull");
	}

	@Test
	public void createCustomer() throws Exception {
		String raw = "{\n" + "        \"name\": \"Yehynny\",\n" + "        \"email\": \"jas@mail.com\",\n"
				+ "        \"products\": []\n" + "    }";
		String jsonResponse = mockMvc
				.perform(post("/customer/").content(raw).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Customer cus = new ObjectMapper().readValue(jsonResponse, Customer.class);
		Assert.assertNotNull(cus);
		Assert.assertFalse(cus.getName().isEmpty());
		System.out.println("Exito en createCustomer con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void updateCustomer() throws Exception {
		String raw = "{\n \"customerId\":1," + "        \"name\": \"Marce\",\n"
				+ "        \"email\": \"marcecorreo@mail.com\",\n" + "        \"products\": []\n" + "    }";
		String jsonResponse = mockMvc
				.perform(put("/customer/").content(raw).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Customer cus = new ObjectMapper().readValue(jsonResponse, Customer.class);
		Assert.assertNotNull(cus);
		Assert.assertFalse(cus.getName().isEmpty());
		System.out.println("Exito en updateCustomer con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void updateCustomerNull() throws Exception {
		String raw = "{\n \"customerId\":1000," + "        \"name\": \"MarceNosave\",\n"
				+ "        \"email\": \"marcecorreo1@mail.com\",\n" + "        \"products\": []\n" + "    }";
		String jsonResponse = mockMvc
				.perform(put("/customer/").content(raw).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("Exito en updateCustomerNull con los salida: " + jsonResponse + ".");
	}

	@Test
	public void deleteCustomer() throws Exception {
		String jsonResponse = mockMvc
				.perform(delete("/customer/" + 2).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Assert.assertTrue(jsonResponse.equals("true"));
		System.out.println("Exito en deleteCustomer con salida: \n " + jsonResponse);
	}

	@Test
	public void deleteCustomerNull() throws Exception {
		String jsonResponse = mockMvc
				.perform(delete("/customer/" + 200).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Assert.assertFalse(jsonResponse.equals("true"));
		System.out.println("Exito en deleteCustomerNull con salida: \n " + jsonResponse);
	}
}
