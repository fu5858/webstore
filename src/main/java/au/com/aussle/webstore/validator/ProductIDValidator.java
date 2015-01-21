package au.com.aussle.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import au.com.aussle.webstore.domain.Product;
import au.com.aussle.webstore.exception.ProductNotFoundException;
import au.com.aussle.webstore.service.ProductService;

public class ProductIDValidator implements ConstraintValidator<ProductID, String> {
	
	@Autowired
	private ProductService productService;

	public void initialize(ProductID constraintAnnotation) {
		
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
			Product product;
			
			try{
				product = productService.getProductById(value);
			}catch(ProductNotFoundException e){
				return true;
			}
			
			if(product != null){
				return false;
			}
		
		return true;
	}

}
