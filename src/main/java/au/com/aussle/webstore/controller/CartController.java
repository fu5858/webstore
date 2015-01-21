package au.com.aussle.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/cart")
public class CartController {

	@RequestMapping
	public String get(HttpServletRequest request){
		return "redirect:/cart/" + request.getSession(true).getId();
	}
	
	@RequestMapping(value="/{cartID}", method=RequestMethod.GET)
	public String getCart(@PathVariable(value="cartID") String cartID, Model model){
		
		model.addAttribute("cartID", cartID);
		
		return "cart";
	}
}
