package au.com.aussle.webstore.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

@Controller
public class HomeController {

	@RequestMapping("/welcome")
	public String welcome(Model model){
		model.addAttribute("greeting", "Welcome to Web Store!");
		model.addAttribute("tagline", "The one and only amazing web store");
		
		return "redirect:/welcome/greeting";
//		return "forward:/welcome/greeting";
//		return "welcome";
	}
	
	@RequestMapping("/home")
	public ModelAndView greeting(Map<String, Object> model){
		model.put("greeting", "Welcome to Web Store!");
		model.put("tagline", "The one and onlyh amazing web store");
		
		View view = new InternalResourceView("/WEB-INF/views/welcome.jsp");
		
		return new ModelAndView(view, model);
	}
	
	@RequestMapping("/welcome/greeting")
	public String greeting(){
		return "welcome";
	}
}
