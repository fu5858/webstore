package au.com.aussle.webstore.repository;

import au.com.aussle.webstore.domain.Order;

public interface OrderRepository {
	Long saveOrder(Order order);
}
