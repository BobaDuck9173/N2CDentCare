package com.example.N2CDentCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.N2CDentCare.model.Account;
import com.example.N2CDentCare.model.DatLich;
import com.example.N2CDentCare.model.Dichvu;
import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.model.GioLamViec;
import com.example.N2CDentCare.repositories.DatLichRepository;
import com.example.N2CDentCare.repositories.DichvuRepository;
import com.example.N2CDentCare.repositories.DoctorRepository;
import com.example.N2CDentCare.repositories.GioLamViecRepository;


@Controller
public class appointmentController {
	private Account user;
	
	@Autowired
	DichvuRepository dichvuRepository;	
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	GioLamViecRepository gioLamViecRepository;
	
	@Autowired
	DatLichRepository datLichRepository;
	
	@GetMapping("/dat-lich")
	public String getDichvu(Model model){
		List<Dichvu> list = dichvuRepository.findAll();
		model.addAttribute("dichvus", list);
		
		List<Doctor> dList = doctorRepository.findAll();
		model.addAttribute("doctors", dList);
		
		List<GioLamViec> glvList = gioLamViecRepository.findAll();
		model.addAttribute("glvlist", glvList);
	
		model.addAttribute("datLich", new DatLich());
		user = dashboardController.getUser();
		model.addAttribute("user", user);
		System.out.println(user);
		return "appointment";
	}
	@PostMapping("/dat-lich")
	public String datLichKham(@ModelAttribute("datLich") DatLich datLich, Model model) {
	    try {
	    	 
	        datLichRepository.save(datLich);
	        model.addAttribute("notification", "success");
	        model.addAttribute("message", "Đặt lịch khám thành công!");
	    } catch (Exception e) {
	        model.addAttribute("notification", "error");
	        model.addAttribute("message", "Đã xảy ra lỗi khi đặt lịch: " + e.getMessage());
	    }

	    // Load lại dữ liệu cần thiết để hiển thị form
	    model.addAttribute("dichvus", dichvuRepository.findAll());
	    model.addAttribute("doctors", doctorRepository.findAll());
	    model.addAttribute("glvlist", gioLamViecRepository.findAll());

	    return "appointment";
	}
}
