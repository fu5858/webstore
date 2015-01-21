package au.com.aussle.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.aussle.webstore.domain.Order;
import au.com.aussle.webstore.domain.Product;
import au.com.aussle.webstore.repository.OrderRepository;
import au.com.aussle.webstore.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartService cartService;
	
	public void processOrder(String productID, long quantity) {

		Product productById = productRepo.getProductById(productID);
		
		if(productById.getUnitsInStock() < quantity){
			throw new IllegalArgumentException("Out of Stock. Available Units in stock" + productById.getUnitsInStock());
		}
		
		productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
	}

	public Long saveOrder(Order order) {
		
		Long orderID = orderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartID());
		
		return orderID;
	}

}
