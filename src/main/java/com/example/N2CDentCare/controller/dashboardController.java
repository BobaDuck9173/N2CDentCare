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
	private static boolean loginByCustomer;
	
	@Autowired
	BenhNhanRepository benhNhanRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	BenhAnRepository benhAnRepository;
	
	@Autowired
	ViewBenhAnRepository viewBenhAnRepository;
	
	@GetMapping("/nhan-vien/dang-nhap")
	public String dangNhapNV(Model model){
		Account account = new Account();
		model.addAttribute("account", account);
		model.addAttribute("loginResult", "0");
		user = null;
		loginByCustomer = false;
		return "/nhan-vien/login";
	}
	
	@GetMapping("/dang-nhap")
	public String dangNhapKH(Model model){
		Account account = new Account();
		model.addAttribute("account", account);
		model.addAttribute("loginResult", "0");
		user = null;
		loginByCustomer = true;
		model.addAttribute("loginByCustomer", loginByCustomer);
		return "/nhan-vien/login";
	}
	
	@PostMapping("/dang-nhap")
	public String xuLyDangNhapKH(@ModelAttribute("account") Account account, Model model) {
		//TODO: process POST request
		String result = dangNhapByKH(account, model);
		if (result != null)
			return result;
		result = thongBaoDangNhap(account, model);
		loginByCustomer = true;
		model.addAttribute("loginByCustomer", loginByCustomer);
		return result;
	}
	
	
	@PostMapping("/nhan-vien/dang-nhap")
	public String xuLyDangNhapNV(@ModelAttribute("account") Account account, Model model) {
		//TODO: process POST request
		String result = dangNhapByNV(account, model);
		if (result != null)
			return result;
		result =  thongBaoDangNhap(account, model);
		loginByCustomer = false;
		model.addAttribute("loginByCustomer", loginByCustomer);
		return result;
	}
	
	@GetMapping("/nhan-vien/quan-ly")
	public String trangQuanLy(Model model){
		if (user == null) {
			return "redirect:/nhan-vien/dang-nhap";
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
	
	private String thongBaoDangNhap(@ModelAttribute("account") Account account, Model model) {
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
	
	private String dangNhapByNV(@ModelAttribute("account") Account account, Model model) {
		List<Account> listNv = accountRepository.findAll();
		for (int i = 0; i < listNv.size(); i++) {
			Account check = listNv.get(i);
			if (account.getUsername().equals(check.getUsername())) {
				if (account.getPassword().equals(check.getPassword())) {
					user = check;
					return "redirect:/nhan-vien/quan-ly";
				}
			}
		}
		return null;
	}
	
	private String dangNhapByKH(@ModelAttribute("account") Account account, Model model) {
		List<Account> listKh = accountRepository.findAll();
		for (int i = 0; i < listKh.size(); i++) {
			Account check = listKh.get(i);
			if (account.getUsername().equals(check.getUsername())) {
				if (account.getPassword().equals(check.getPassword())) {
					user = check;
					return "redirect:/trang-chu";
				}
			}
		}
		return null;
	}
	
	public static Account getUser() {
		return user;
	}
	
}
