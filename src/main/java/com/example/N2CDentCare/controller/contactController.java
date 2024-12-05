package com.example.N2CDentCare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class contactController {
	
	@GetMapping("/lien-he")
	public String getContact(Model model){
		return "contact";
	}
}
