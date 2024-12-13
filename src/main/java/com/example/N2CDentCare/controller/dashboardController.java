package com.example.N2CDentCare.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.N2CDentCare.model.Account;
import com.example.N2CDentCare.model.BenhAn;
import com.example.N2CDentCare.model.BenhNhan;
import com.example.N2CDentCare.model.ViewBenhAn;
import com.example.N2CDentCare.repositories.AccountRepository;
import com.example.N2CDentCare.repositories.BenhAnRepository;
import com.example.N2CDentCare.repositories.BenhNhanRepository;
import com.example.N2CDentCare.repositories.ViewBenhAnRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class dashboardController {
	
	private static Account user;
	
	@Autowired
	BenhNhanRepository benhNhanRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	BenhAnRepository benhAnRepository;
	
	@Autowired
	ViewBenhAnRepository viewBenhAnRepository;
	
	@GetMapping("/dang-nhap")
	public String dangNhap(Model model){
		Account account = new Account();
		model.addAttribute("account", account);
		model.addAttribute("loginResult", "0");
		model.addAttribute("isSignIn", true);
		model.addAttribute("isSignUp", false);
		user = null;
		return "/nhan-vien/login";
	}
	
	@GetMapping("/dang-ky")
	public String dangKy(Model model){
		Account account = new Account();
		model.addAttribute("account", account);
		model.addAttribute("loginResult", "0");
		model.addAttribute("isSignIn", false);
		model.addAttribute("isSignUp", true);
		user = null;
		return "/nhan-vien/login";
	}
	
	@PostMapping("/dang-nhap")
	public String xuLyDangNhap(@ModelAttribute("account") Account account, Model model) {
		//TODO: process POST request		
		String noti = null;
		
		if (!(account.getUsername() != "")) {
			noti = "Vui lòng nhập tài khoản";
			model.addAttribute("notification", noti);
		}else if (!(account.getPassword() != "")) {
			noti = "Vui lòng nhập mật khẩu";
			model.addAttribute("notification", noti);
		} else {
			List<Account> resultList = accountRepository.findByUsername(account.getUsername()); 
			if(resultList.size() > 0) {
				Account check = resultList.get(0);
				if (account.getPassword().equals(check.getPassword())) {
					user = check;
					List<BenhNhan> checkBenhNhan = benhNhanRepository.findBySdt(account.getUsername());
					if (checkBenhNhan.size() > 0) {
						return "redirect:/trang-chu";
					} else {
						return "redirect:/nhan-vien/quan-ly";
					}
				}
			}
			
			noti = "Sai tài khoản hoặc mật khẩu";
			model.addAttribute("notification", noti);
		}
		
		return "/nhan-vien/login";
	}
		
	
	
	
	@GetMapping("/nhan-vien/quan-ly")
	public String trangQuanLy(Model model){
		if (user == null) {
			return "redirect:/dang-nhap";
		}
		model.addAttribute("benhNhan", new BenhNhan());
		model.addAttribute("danhSachBenhAn", null);
		model.addAttribute("benhAn", new BenhAn());
		return "/nhan-vien/admin";
	}
	
	@PostMapping("/tim-benh-nhan")
	public String formTimBenhNhan(@ModelAttribute("benhNhan") BenhNhan benhNhan, Model model) {
		//TODO: process POST request
		List<BenhNhan> list = benhNhanRepository.findBySdt(benhNhan.getSdt());
		BenhNhan result = null;
		if (list.size() > 0) {
			result = list.get(0);
			model.addAttribute("thongTinBenhNhan", result);
			
			
			List<ViewBenhAn> danhSachBenhAn = viewBenhAnRepository.findBySdt(benhNhan.getSdt());
			if(danhSachBenhAn.size() > 0)
				model.addAttribute("danhSachBenhAn", danhSachBenhAn);
			else model.addAttribute("danhSachBenhAn", null);
		}else {
			model.addAttribute("thongTinBenhNhan", "0");
		}
		model.addAttribute("benhAn", new BenhAn(result.getSdt(), user.getId(), "",""));
		return "/nhan-vien/admin";
	}
	
	@PostMapping("/them-benh-nhan")
	public String formThemBenhNhan(@ModelAttribute("benhNhan") BenhNhan benhNhan, Model model) {
		//TODO: process POST request
		String hoTen = benhNhan.getHoTen();
		String sdt = benhNhan.getSdt();
		boolean gioiTinh = benhNhan.getGioiTinh();
		String diaChi = benhNhan.getDiaChi();
		benhNhanRepository.save(new BenhNhan(diaChi, gioiTinh, hoTen, sdt));
		return "/nhan-vien/admin";
	}
	
	
	public static Account getUser() {
		return user;
	}
	
}
