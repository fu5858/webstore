package au.com.aussle.webstore.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import au.com.aussle.webstore.domain.Order;

@Repository
public class InMemoryOrderRepositoryImpl implements OrderRepository {

	private Map<Long, Order> listOfOrders;
	private long nextOrderID;
	
	public InMemoryOrderRepositoryImpl() {
		listOfOrders = new HashMap<Long, Order>();
		nextOrderID = 1000;
	}

	public Long saveOrder(Order order) {
		order.setOrderID(getNextOrderID());
		listOfOrders.put(order.getOrderID(), order);
		
		return order.getOrderID();
	}

	private synchronized long getNextOrderID(){
		return nextOrderID++;
	}
}
