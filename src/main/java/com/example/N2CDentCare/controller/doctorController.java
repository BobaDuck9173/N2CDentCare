package com.example.N2CDentCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.repositories.DoctorRepository;


@RestController
public class doctorController {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@GetMapping("/getdata")
	List<Doctor> getDoctors(){
		return doctorRepository.findAll();
	}
}
