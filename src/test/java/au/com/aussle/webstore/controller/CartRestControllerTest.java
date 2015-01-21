package au.com.aussle.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-spring-servlet.xml")
@WebAppConfiguration
public class CartRestControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	MockHttpSession session;
	
	private MockMvc mockMVC;
	
	@Before
	public void setUp(){
		this.mockMVC = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void read_method_should_return_correct_cart_Json_object() throws Exception {
		System.out.println("## 1st request ##");
		this.mockMVC.perform(put("/rest/cart/add/P1234").session(session))
		.andExpect(status().is(204));

		System.out.println();
		System.out.println("## 2nd request ##");
		this.mockMVC.perform(get("/rest/cart/" + session.getId()).session(session))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cartItems.P1234.product.productID").value("P1234"));
		
	}
}
