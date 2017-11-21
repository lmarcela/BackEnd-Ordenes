package com.marcela;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.junit.runners.MethodSorters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.marcela.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringOrdenesApplicationTests {
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc,mockMvc2;

    /*@Before
    public void beforeClassMethod() throws Exception {
    }*/
    
	@Test
	public void contextLoads() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        jsonResponse = this.mockMvc
				.perform(get("/buscarCliente?cliente=" + 1).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("Fin Before");
		System.out.println("Fin context loads");
	}

	/*@RequestMapping(value = "/buscarCliente",method = RequestMethod.GET)
	public ResponseEntity<Customer> getBuscarCliente(int cliente) {
		return new ResponseEntity<Customer>(customerService.getBuscarCliente(cliente), HttpStatus.OK);
	}*/
	
	Customer cus;
	static String jsonResponse; 
	
	
	@Test
	public void test1GetBuscarClienteExistente() throws Exception {
		cus = new ObjectMapper().readValue(jsonResponse, Customer.class);
		Assert.assertNotNull(cus);
		Assert.assertFalse(cus.getName().isEmpty());
	}

	@Test
	public void test3GetBuscarClienteNulo() throws Exception {

        this.mockMvc2 = MockMvcBuilders.webAppContextSetup(this.wac).build();
		Assert.assertTrue(this.mockMvc2.perform(get("/buscarCliente?cliente=" + 100)
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
	    ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString().isEmpty());		
	}
	
	@Test
	public void test2GetBuscarClienteJson() throws Exception {
		System.out.println("DATOS "+JsonPath.parse(jsonResponse).json());
	}
}
