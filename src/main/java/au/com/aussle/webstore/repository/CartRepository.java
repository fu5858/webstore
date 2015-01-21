package au.com.aussle.webstore.repository;

import au.com.aussle.webstore.domain.Cart;

public interface CartRepository {

	Cart create(Cart cart);
	Cart read(String cartID);
	void update(String cartID, Cart cart);
	void delete(String cartID);
}
