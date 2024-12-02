package com.example.N2CDentCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.N2CDentCare.model.BangGiaRangSu;
import com.example.N2CDentCare.model.Dichvu;
import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.repositories.BangGIaRangSuRepository;
import com.example.N2CDentCare.repositories.DichvuRepository;
import com.example.N2CDentCare.repositories.DoctorRepository;


@Controller
public class serviceController {
	
	@Autowired
	DichvuRepository dichvuRepository;
	
	@Autowired
	BangGIaRangSuRepository bangGiaRangSuRepository;
	
	@GetMapping("/dich-vu/boc-rang-su")
	public String bocRangSu(Model model) {
		List<BangGiaRangSu> list = bangGiaRangSuRepository.findAll();
		model.addAttribute("banggia", list);
		return "/dich-vu/boc-rang-su";
	}
	
	@GetMapping("/dich-vu/bang-gia-boc-rang-su")
	public String bangGiaBocRangSu(Model model) {

		return "/dich-vu/bang-gia-boc-rang-su";
	}
	
	@GetMapping("/dich-vu/dan-su-venner")
	public String danSuVenner(Model model) {

		return "/dich-vu/dan-su-venner";
	}
	
	@GetMapping("/dich-vu/nieng-rang-tham-my")
	public String niengRangThamMy(Model model) {
		String dv = "Niềng răng thẩm mỹ";
		model.addAttribute("title", dv);
		return "/dich-vu/dich-vu-khac";
	}
	
	@GetMapping("/dich-vu/tram-rang-tham-my")
	public String tramRangThamMy(Model model) {
		String dv = "Trám răng thẩm mỹ";
		model.addAttribute("title", dv);
		return "/dich-vu/dich-vu-khac";
	}
	
	@GetMapping("/dich-vu/cao-voi-rang")
	public String caoVoiRang(Model model) {
		String dv = "Cạo vôi răng";
		model.addAttribute("title", dv);
		return "/dich-vu/dich-vu-khac";
	}
	
	private String formatGia(String gia) {
		
		switch (Integer.parseInt(gia)) {
			case 1000:
				break;
			case 10000:
				break;
			case 100000:
				break;
			case 1000000:
				break;
			case 10000000:
				break;
			case 100000000:
				break;
		}
		
		return null;
	}
	
}
