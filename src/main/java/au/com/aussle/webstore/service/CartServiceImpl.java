package au.com.aussle.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.aussle.webstore.domain.Cart;
import au.com.aussle.webstore.exception.InvalidCartException;
import au.com.aussle.webstore.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepo;
	
	public Cart create(Cart cart) {
		return cartRepo.create(cart);
	}

	public Cart read(String cartID) {
		return cartRepo.read(cartID);
	}

	public void update(String cartID, Cart cart) {
		cartRepo.update(cartID, cart);
	}

	public void delete(String cartID) {
		cartRepo.delete(cartID);
	}

	public Cart validate(String cartID) {
		Cart cart = cartRepo.read(cartID);
		
		if(cartID == null || cart.getCartItems().size() == 0){
			throw new InvalidCartException(cartID);
		}
		
		return cart;
	}

}
