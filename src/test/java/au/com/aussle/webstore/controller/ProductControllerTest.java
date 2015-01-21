package au.com.aussle.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import au.com.aussle.webstore.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-spring-servlet.xml")
@WebAppConfiguration
public class ProductControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMVC;
	
	@Before
	public void setUp(){
		this.mockMVC = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testGetProducts() throws Exception {
		this.mockMVC.perform(get("/products")).andExpect(model().attributeExists("products"));
	}
	
	@Test
	public void testGetProductByID() throws Exception {
		Product product = new Product("P1234", "iPhone s5", new BigDecimal(500));
		
		this.mockMVC.perform(get("/products/product").param("id", "P1234"))
		.andExpect(model().attributeExists("product"))
		.andExpect(model().attribute("product", product));
	}
}
