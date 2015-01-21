package au.com.aussle.webstore.service;

import au.com.aussle.webstore.domain.Cart;

public interface CartService {
	Cart create(Cart cart);
	Cart read(String cartID);
	void update(String cartID, Cart cart);
	void delete(String cartID);
	Cart validate(String cartID);
}
