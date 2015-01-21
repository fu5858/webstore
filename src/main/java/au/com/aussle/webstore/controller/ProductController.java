package au.com.aussle.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import au.com.aussle.webstore.domain.Product;
import au.com.aussle.webstore.exception.NoProductsFoundUnderCategoryException;
import au.com.aussle.webstore.exception.ProductNotFoundException;
import au.com.aussle.webstore.service.ProductService;
import au.com.aussle.webstore.validator.ProductValidator;
import au.com.aussle.webstore.validator.UnitsInStockValidator;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private UnitsInStockValidator unitsInStockValidator;
	@Autowired
	private ProductValidator productValidator;
	
	@RequestMapping
	public String list(Model model){
		model.addAttribute("products", productService.getAllProducts());
		
		return "products";
	}
	
	@RequestMapping("/all")
	public ModelAndView allProducts(Model model){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("products", productService.getAllProducts());
		modelAndView.setViewName("products");
		
		return modelAndView;
	}
	
	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory){
		List<Product> products = productService.getProductsByCategory(productCategory);
		
		if(products == null || products.isEmpty()){
			throw new NoProductsFoundUnderCategoryException();
		}
		
		model.addAttribute("products", products);
		
		return "products";
	}
	
	@RequestMapping("/filter/{byCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar="byCriteria") Map<String, List<String>> filterParams, Model model){
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		
		return "products";
	}
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productID, Model model){
		model.addAttribute("product", productService.getProductById(productID));
		
		return "product";
	}
	
	@RequestMapping("/tablet/{price}")
	public String filterProducts(@MatrixVariable(pathVar="price") Map<String, List<String>> filterParams
			, @RequestParam("manufacturer") String manufacturer, Model model){
		System.out.println("Called filterProducts Method!");
		System.out.println("String parameter => " + manufacturer);
		
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		
		return "products";
	}
	
	// from chapter 04
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		
		return "addProduct";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result, HttpServletRequest request){
		String[] suppressedFields = result.getSuppressedFields();
		
//		System.out.println(suppressedFields.length);
//		
//		for(String what : suppressedFields){
//			System.out.println(what);
//		}
		
		if(result.hasErrors()){
			return "addProduct";
		}
		
		if(suppressedFields.length > 0){
			throw new RuntimeException("Attempting to bind disaallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		MultipartFile productImage = newProduct.getProductImage();
		String rootDir = request.getSession().getServletContext().getRealPath("/");
		
		if(productImage != null && !productImage.isEmpty()){
			try{
				productImage.transferTo(new File(rootDir + "resources\\images\\" + newProduct.getProductID() + ".png"));
				System.out.println("File saved");
			}catch(Exception e){
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		
//		MultipartFile productPDF = newProduct.getProductPDF();
//		
//		if(productPDF  != null && !productPDF.isEmpty()){
//			try{
//				productPDF.transferTo(new File(rootDir + "resources\\pdf\\" + newProduct.getProductID() + ".pdf"));
//			}catch(Exception e){
//				throw new RuntimeException("Product PDF saving failed", e);
//			}
//		}
		
		productService.addProduct(newProduct);
		
		return "redirect:/products";
	}
	
	@RequestMapping("/invalidPromoCode")
	public String invalidPromoCode(){
		return "invalidPromoCode";
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, ProductNotFoundException exception){
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductID", exception.getProductID());
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
		mav.setViewName("productNotFound");
		
		return mav;
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder){
//		binder.setDisallowedFields("unitsInOrder", "discontinued");
//		binder.setAllowedFields("productID","name","unitPrice","description","manufacturer","category","unitsInStock", "condition","productImage","productPDF");
		binder.setAllowedFields("productID","name","unitPrice","description","manufacturer","category","unitsInStock", "condition","productImage","language");
		binder.setValidator(unitsInStockValidator);
		binder.setValidator(productValidator);
	}
}
