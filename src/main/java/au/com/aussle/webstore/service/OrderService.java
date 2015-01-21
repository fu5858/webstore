package au.com.aussle.webstore.service;

import au.com.aussle.webstore.domain.Order;

public interface OrderService {

	void processOrder(String productID, long quantity);
	Long saveOrder(Order order);
}
