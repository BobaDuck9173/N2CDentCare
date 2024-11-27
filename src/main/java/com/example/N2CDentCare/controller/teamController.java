package com.example.N2CDentCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.repositories.DoctorRepository;


@Controller
public class teamController {
	
	@Autowired
	DoctorRepository doctorRepository;
	@GetMapping("/gioi-thieu/bac-si")
	public String getDoctors(Model model){
		List<Doctor> list = doctorRepository.findAll();
		model.addAttribute("doctors", list);
		return "team";
	}
}
