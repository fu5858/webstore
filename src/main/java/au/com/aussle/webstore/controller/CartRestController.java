package au.com.aussle.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import au.com.aussle.webstore.domain.Cart;
import au.com.aussle.webstore.domain.CartItem;
import au.com.aussle.webstore.domain.Product;
import au.com.aussle.webstore.exception.ProductNotFoundException;
import au.com.aussle.webstore.service.CartService;
import au.com.aussle.webstore.service.ProductService;

@Controller
@RequestMapping(value="rest/cart")
public class CartRestController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Cart create(@RequestBody Cart cart){
		return cartService.create(cart);
	}
	
	@RequestMapping(value="/{cartID}", method=RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value="cartID") String cartID){
		return cartService.read(cartID);
	}
	
	@RequestMapping(value="/{cartID}", method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value="cartID") String cartID, @RequestBody Cart cart){
		
		cartService.update(cartID, cart);
	}
	
	@RequestMapping(value="/{cartID}", method=RequestMethod.DELETE)
	public void delete(@PathVariable(value="cartID") String cartID){

		cartService.delete(cartID);
	}
	
	@RequestMapping(value="/add/{productID}", method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable String productID, HttpServletRequest request){
		logger.info("product ID : " + productID);
		
		String sessionID = request.getSession(true).getId();
		
		logger.info("sesseion ID => " + sessionID);
		
		Cart cart = cartService.read(sessionID);
		
		logger.info("Cart Status : " + ((cart == null) ? "null" : "not null"));
		
		if(cart == null){
			cart = cartService.create(new Cart(sessionID));
		}
		
		logger.info("## before add a cartitem with cart object ##");
		logger.info(cart);

		Product product = productService.getProductById(productID);
		
		logger.info("## product object #");
		logger.info(product);
		
		if(product == null){
			throw new IllegalArgumentException(new ProductNotFoundException(productID));
		}
		
		CartItem newItem = new CartItem(product);
		
		logger.info("CartItem Object => " + newItem);
		
		cart.addCartItem(newItem);
		
		logger.info("## after add a cartitem with cart object ##\n" + cart);
		
		cartService.update(sessionID, cart);
	}
	
	@RequestMapping(value="/remove/{productID}", method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable String productID, HttpServletRequest request){
		String sessionID = request.getSession(true).getId();
		Cart cart = cartService.read(sessionID);
		
		if(cart == null){
			cart = cartService.create(new Cart(sessionID));
		}
		
		Product product = productService.getProductById(productID);
		
		if(product == null){
			throw new IllegalArgumentException(new ProductNotFoundException(productID));
		}

		cart.removeCartItem(new CartItem(product));
		
		cartService.update(sessionID, cart);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Illegal request, please verify your payload")
	public void handleClientErrors(Exception ex){}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")
	public void handleServerErrors(Exception ex){}
}
