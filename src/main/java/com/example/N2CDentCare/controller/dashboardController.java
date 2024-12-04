package com.example.N2CDentCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.N2CDentCare.model.Account;
import com.example.N2CDentCare.model.BenhNhan;
import com.example.N2CDentCare.model.Dichvu;
import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.model.GioLamViec;
import com.example.N2CDentCare.repositories.AccountRepository;
import com.example.N2CDentCare.repositories.BenhNhanRepository;
import com.example.N2CDentCare.repositories.DichvuRepository;
import com.example.N2CDentCare.repositories.DoctorRepository;
import com.example.N2CDentCare.repositories.GioLamViecRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class dashboardController {
	
	private Account user;
	
	@Autowired
	BenhNhanRepository benhNhanRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("/nhan-vien/dang-nhap")
	public String dangNhapNv(Model model){
		Account account = new Account();
		model.addAttribute("account", account);
		model.addAttribute("loginResult", "0");
		user = null;
		return "/nhan-vien/login";
	}
	
	@PostMapping("/nhan-vien/dang-nhap")
	public String formDangNhap(@ModelAttribute("account") Account account, Model model) {
		//TODO: process POST request
		List<Account> listNv = accountRepository.findAll();
		for (int i = 0; i < listNv.size(); i++) {
			Account check = listNv.get(i);
			if (account.getUsername().equals(check.getUsername())) {
				if (account.getPassword().equals(check.getPassword()))
					user = check;
					return "redirect:/nhan-vien/quan-ly";
			}
		}
		String noti = "";
		if (!(account.getUsername() != "")) {
			noti = "Vui lòng nhập tài khoản";
			model.addAttribute("notification", noti);
		}else if (!(account.getPassword() != "")) {
			noti = "Vui lòng nhập mật khẩu";
			model.addAttribute("notification", noti);
		}else {
			noti = "Sai tài khoản hoặc mật khẩu";
			model.addAttribute("notification", noti);
		}
		return "/nhan-vien/login";
	}
	
	@GetMapping("/nhan-vien/quan-ly")
	public String trangQuanLy(Model model){
		if (user == null) {
			
			return "redirect:/nhan-vien/dang-nhap";
		}
		BenhNhan bn = new BenhNhan();
		model.addAttribute("benhNhan", bn);
		return "/nhan-vien/admin";
	}
	
	@PostMapping("/tim-benh-nhan")
	public String formDangNhap(@ModelAttribute("benhNhan") BenhNhan benhNhan, Model model) {
		//TODO: process POST request
		List<BenhNhan> list = benhNhanRepository.findBySdt(benhNhan.getSdt());
		BenhNhan result = null;
		if (list.size() > 0) {
			result = list.get(0);
			model.addAttribute("thongTinBenhNhan", result);
		}else {
			model.addAttribute("thongTinBenhNhan", "0");
		}
		
		return "/nhan-vien/admin";
	}
}
