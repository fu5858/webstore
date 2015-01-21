package au.com.aussle.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import au.com.aussle.webstore.domain.Product;

public interface ProductService {

	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	Product getProductById(String productID);
	List<Product> getProductsByManufacturer(String manufacturer);
	void addProduct(Product product);
}
