package fr.univrouen.rss22.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index.html");
		return mav;
	}
	
	@GetMapping("/help")
	public ModelAndView help() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("help.html");
		return mav;
	}
	
}