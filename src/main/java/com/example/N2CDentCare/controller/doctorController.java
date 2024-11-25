package com.example.N2CDentCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.repositories.DoctorRepository;


@Controller
public class doctorController {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@GetMapping("/team.html")
	public String getDoctors(Model model){
		List<Doctor> list = doctorRepository.findAll();
		
		model.addAttribute("firstDr", list.get(0).getHoTen());
		model.addAttribute("secondDr", "cuong");
		model.addAttribute("thirdDr", "cong");
		model.addAttribute("fourthDr", list.get(1).getHoTen());
		model.addAttribute("fifthDr", "bs thu 5");
		
		return "team";
	}
}
