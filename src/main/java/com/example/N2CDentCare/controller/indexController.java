package com.example.N2CDentCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.N2CDentCare.model.Dichvu;
import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.repositories.DichvuRepository;
import com.example.N2CDentCare.repositories.DoctorRepository;

@Controller
public class indexController {
	
	@Autowired
	DichvuRepository dichvuRepository;	
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@GetMapping("/index.html")
	public String getDichvu(Model model){
		List<Dichvu> list = dichvuRepository.findAll();
		model.addAttribute("dichvus", list);
		
		List<Doctor> dList = doctorRepository.findAll();
		model.addAttribute("doctors", dList);
		return "index";
	}
}
