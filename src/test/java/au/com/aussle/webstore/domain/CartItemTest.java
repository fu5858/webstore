package au.com.aussle.webstore.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartItemTest {

	private CartItem cartItem;
	
	@Before
	public void setUp(){
		cartItem = new CartItem();
	}
	
	@Test
	public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity(){
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		
		cartItem.setProduct(iphone);
		
		BigDecimal totalPrice = cartItem.getTotalPrice();
		
		System.out.println(cartItem.getProduct());
		System.out.println(cartItem);
		
		Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
	}
}
