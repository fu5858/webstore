package au.com.aussle.webstore.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import au.com.aussle.webstore.domain.Cart;

@Repository
public class InMemoryCartRepository implements CartRepository {

	private Map<String, Cart> listOfCarts;
	
	public InMemoryCartRepository() {
		listOfCarts = new HashMap<String, Cart>();
	}
	
	public Cart create(Cart cart) {
		
		if(listOfCarts.keySet().contains(cart.getCartID())){
			throw new IllegalArgumentException(String.format("Can not create a cart. A cart with the give id (%) aldrady exist", cart.getCartID()));
		}
		
		listOfCarts.put(cart.getCartID(), cart);
		
		return cart;
	}

	public Cart read(String cartID) {

		return listOfCarts.get(cartID);
	}

	public void update(String cartID, Cart cart) {
		
		if(!listOfCarts.keySet().contains(cartID)){
			throw new IllegalArgumentException(String.format("Can not update a cart. A cart with the give id (%) aldrady exist", cartID));
		}
		
		listOfCarts.put(cartID, cart);
	}

	public void delete(String cartID) {
		
		if(!listOfCarts.keySet().contains(cartID)){
			throw new IllegalArgumentException(String.format("Can not delete a cart. A cart with the give id (%) aldrady exist", cartID));
		}

		listOfCarts.remove(cartID);
	}

}
