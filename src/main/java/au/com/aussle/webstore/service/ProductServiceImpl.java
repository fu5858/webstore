package au.com.aussle.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.aussle.webstore.domain.Product;
import au.com.aussle.webstore.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepo;

	public List<Product> getAllProducts() {
		return productRepo.getAllProducts();
	}

	public List<Product> getProductsByCategory(String category) {
		return productRepo.getProductsByCategory(category);
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productRepo.getProductsByFilter(filterParams);
	}

	public Product getProductById(String productID) {
		// TODO Auto-generated method stub
		return productRepo.getProductById(productID);
	}

	public List<Product> getProductsByManufacturer(String manufacturer) {
		return productRepo.getProductsByManufacturer(manufacturer);
	}

	public void addProduct(Product product) {
		productRepo.addProduct(product);
	}

}
