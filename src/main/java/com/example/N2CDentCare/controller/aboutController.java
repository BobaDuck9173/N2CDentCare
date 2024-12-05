package com.example.N2CDentCare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class aboutController {
	
	@GetMapping("/gioi-thieu/thong-tin-phong-kham")
	public String getAbout(Model model){
		return "/gioi-thieu/about";
	}
}
