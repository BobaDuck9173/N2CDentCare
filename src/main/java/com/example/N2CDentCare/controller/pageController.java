package com.example.N2CDentCare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageController {
//	@GetMapping("/index.html")
//	public String homePage() {
//		return "index";
//	}
	
	@GetMapping("/about.html")
	public String aboutPage() {
		return "about";
	}
	
	@GetMapping("/contact.html")
	public String contactPage() {
		return "contact";
	}
	
	@GetMapping("/price.html")
	public String pricePage() {
		return "price";
	}
	
//	@GetMapping("/service.html")
//	public String servicePage() {
//		return "service";
//	}
	
//	@GetMapping("/team.html")
//	public String teamPage() {
//		return "team";
//	}
	
	@GetMapping("/appointment.html")
	public String appointmentPage() {
		return "appointment";
	}
	
	@GetMapping("/testimonial.html")
	public String testimonialPage() {
		return "testimonial";
	}
}
