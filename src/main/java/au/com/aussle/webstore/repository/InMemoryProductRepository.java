package au.com.aussle.webstore.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import au.com.aussle.webstore.domain.Product;
import au.com.aussle.webstore.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	
	private List<Product> listOfProducts = new ArrayList<Product>();

	public InMemoryProductRepository() {
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640 x 1136 display and 9-megapixel rear camera");
		iphone.setCategory("smartphone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		
		Product laptop_dell = new Product("P1235","Dell Inspiron", new BigDecimal(700));
		laptop_dell.setDescription("Dell Inspiron 14-inch Laptop(Black) with 3rd Generation Intel Core processors");
		laptop_dell.setCategory("laptop");		
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(1000);
		
		Product tablet_Nexus = new Product("P1236","Nexus 7", new BigDecimal(300));
		tablet_Nexus.setDescription("Google Nexus 7 is the lightest 7 inch tablet With a quad-core Qualcomm Snapdragon¢â S4 Proprocessor");
		tablet_Nexus.setCategory("tablet");
		tablet_Nexus.setManufacturer("Google");
		tablet_Nexus.setUnitsInStock(1000);

		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet_Nexus);	
	}

	public List<Product> getAllProducts() {

		return listOfProducts;
	}

	public Product getProductById(String productID) {
		Product productById = null;
		
		for(Product product : listOfProducts){
			if(product != null && product.getProductID() != null && product.getProductID().equals(productID)){
				productById = product;
				break;
			}
		}

		if(productById == null){
			throw new ProductNotFoundException("No products found with the product ID: " + productID);
		}
		
		return productById;
	}

	public List<Product> getProductsByCategory(String category) {
		List<Product> productsByCategory = new ArrayList<Product>();
		
		for(Product product : listOfProducts){
			if(category.equals(product.getCategory())){
				productsByCategory.add(product);
			}
		}
		
		return productsByCategory;
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productByCategory = new HashSet<Product>();
//		Set<Product> productByManufacturer = new HashSet<Product>();
		
		Set<String> criterias = filterParams.keySet();
		double low = 0, high = 0;

		for(Iterator<String> it = criterias.iterator(); it.hasNext(); ){
			System.out.println(it.next());
		}
		
		if(criterias.contains("brand")){
			for(String brandName : filterParams.get("brand")){
				
				for(Product product : listOfProducts){
					if(brandName.equalsIgnoreCase(product.getManufacturer())){
						productsByBrand.add(product);
					}
				}
			}
		}
		
		if(criterias.contains("category")){
			for(String category : filterParams.get("category")){
				productByCategory.addAll(this.getProductsByCategory(category));
			}
		}
		
		if(criterias.contains("low")){
			for(String lowValue : filterParams.get("low")){
				System.out.println(lowValue);
				low = Double.parseDouble(lowValue);
			}
		}
		
		if(criterias.contains("high")){
			for(String highValue : filterParams.get("high")){
				System.out.println(highValue);
				high = Double.parseDouble(highValue);
			}
		}
		
		System.out.println("Price range is " + low + " between " + high);
		
		if(low != 0 || high != 0){
			for(Product product : listOfProducts){
				if(product.getUnitPrice().doubleValue() > low && product.getUnitPrice().doubleValue() < high){
					System.out.print(product.getName() + "\t");
					System.out.println(product.getUnitPrice());
					productByCategory.addAll(this.getProductsByManufacturer(product.getManufacturer()));
				}
			}
		}
		
		productByCategory.retainAll(productsByBrand);
		
		return productByCategory;
	}

	public List<Product> getProductsByManufacturer(String manufacturer) {
		System.out.println("Callsed getProductsByManufacturer(" + manufacturer +") ##");
		List<Product> productsByManufacturer = new ArrayList<Product>();

		for(Product product : listOfProducts){
			if(manufacturer.equals(product.getManufacturer())){
				productsByManufacturer.add(product);
			}
		}
		return productsByManufacturer;
	}

	public void addProduct(Product product) {
		listOfProducts.add(product);
	}

	
}
