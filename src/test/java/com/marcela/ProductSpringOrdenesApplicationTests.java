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
import com.marcela.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductSpringOrdenesApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getProducts() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/products/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Product[] product = new ObjectMapper().readValue(jsonResponse, Product[].class);
		Assert.assertNotNull(product);
		System.out.println(
				"Exito en getProducts con los datos: " + product.length + "\n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void getProduct() throws Exception {
		String jsonResponse = mockMvc
				.perform(get("/product/" + 1).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Product product = new ObjectMapper().readValue(jsonResponse, Product.class);
		Assert.assertNotNull(product);
		Assert.assertFalse(product.getName().isEmpty());
		System.out.println("Exito en getProduct con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void getProductNull() throws Exception {
		Assert.assertTrue(mockMvc
				.perform(get("/product/" + 100).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString().isEmpty());
		System.out.println("Exito en getProductNull");
	}

	@Test
	public void createProduct() throws Exception {
		String raw = "{\"name\":\"Tablet LENOVO Yoga 100 8\\\"\\\"  N\",\"price\":430000.0}";
		String jsonResponse = mockMvc
				.perform(post("/product/").content(raw).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Product product = new ObjectMapper().readValue(jsonResponse, Product.class);
		Assert.assertNotNull(product);
		Assert.assertFalse(product.getName().isEmpty());
		System.out.println("Exito en createProduct con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void createProductNull() throws Exception {
		String raw = "{\"name\":\"Tablet HUAWEI T1-701W WiFi - G\",\"price\":3.5}";
		String jsonResponse = mockMvc
				.perform(post("/product/").content(raw).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("Exito en createProductNull con los datos: " + jsonResponse + ".");
	}

	@Test
	public void updateProduct() throws Exception {
		String raw = "{\"productId\":1,\"name\":\"PC LENOVO Yoga 3 8\\\"\\\"  N\",\"price\":430000.0}";
		String jsonResponse = mockMvc
				.perform(put("/product/").content(raw).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Product product = new ObjectMapper().readValue(jsonResponse, Product.class);
		Assert.assertNotNull(product);
		Assert.assertFalse(product.getName().isEmpty());
		System.out.println("Exito en updateProduct con los datos: \n " + JsonPath.parse(jsonResponse).json());
	}

	@Test
	public void updateProductNull() throws Exception {
		String raw = "{\n \"productId\":1000," + "        \"name\": \"MarceNosave\",\n"
				+ "        \"price\": \"1234\",\n" + "        \"products\": []\n" + "    }";
		String jsonResponse = mockMvc
				.perform(put("/product/").content(raw).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("Exito en updateProductNull con los salida: " + jsonResponse + ".");
	}

	@Test
	public void deleteProduct() throws Exception {
		String jsonResponse = mockMvc
				.perform(delete("/product/5").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("Exito en deleteProduct con salida: \n " + jsonResponse);
		Assert.assertTrue(jsonResponse.equals("true"));
	}

	@Test
	public void deleteProductNull() throws Exception {
		String jsonResponse = mockMvc
				.perform(delete("/product/" + 200).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Assert.assertFalse(jsonResponse.equals("true"));
		System.out.println("Exito en deleteProductNull con salida: \n " + jsonResponse);
	}
}
