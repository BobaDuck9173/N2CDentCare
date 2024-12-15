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
		model.addAttribute("page", "tongQuat");
		model.addAttribute("pageTitle", "Tổng quát");
		model.addAttribute("benhNhan", new BenhNhan());
		model.addAttribute("danhSachBenhAn", null);
		model.addAttribute("benhAn", new BenhAn());
		model.addAttribute("ketQuaTimKiem", null);
		model.addAttribute("titlePanelInfo", "Lịch hẹn hôm nay");
		return "/nhan-vien/index";
	}
	
	@GetMapping("/nhan-vien/quan-ly/benh-nhan")
	public String trangQuanLyBenhNhan(Model model){
		if (user == null) {
			return "redirect:/dang-nhap";
		}
		model.addAttribute("page", "benhNhan");
		model.addAttribute("pageTitle", "Quản lý bệnh nhân");
		model.addAttribute("benhNhan", new BenhNhan());
		model.addAttribute("danhSachBenhAn", null);
		model.addAttribute("benhAn", new BenhAn());
		model.addAttribute("ketQuaTimKiem", null);
		model.addAttribute("titlePanelInfo", "Danh sách bệnh nhân");
		return "/nhan-vien/index";
	}
	
	@GetMapping("/nhan-vien/quan-ly/benh-an")
	public String trangQuanLyBenhAn(Model model){
		if (user == null) {
			return "redirect:/dang-nhap";
		}
		model.addAttribute("page", "benhAn");
		model.addAttribute("pageTitle", "Quản lý bệnh án");
		model.addAttribute("benhNhan", new BenhNhan());
		model.addAttribute("danhSachBenhAn", null);
		model.addAttribute("benhAn", new BenhAn());
		model.addAttribute("ketQuaTimKiem", null);
		model.addAttribute("titlePanelInfo", "Danh sách bệnh án");
		return "/nhan-vien/index";
	}
	
	@PostMapping("/tim-benh-nhan")
	public String formTimBenhNhan(@ModelAttribute("benhNhan") BenhNhan benhNhan, Model model) {
		//TODO: process POST request
		List<BenhNhan> list = benhNhanRepository.findBySdt(benhNhan.getSdt());
		BenhNhan result = null;
		if (list.size() > 0) {
			result = list.get(0);
			model.addAttribute("ketQuaTimKiem", true);
			model.addAttribute("titlePanelInfo", "Thông tin bệnh nhân");
			model.addAttribute("columnTitleFrst", result.getTableColumnTitle());
			model.addAttribute("danhSachBenhNhan", result);

			List<ViewBenhAn> danhSachBenhAn = viewBenhAnRepository.findBySdt(benhNhan.getSdt());
			if(danhSachBenhAn.size() > 0) {
				model.addAttribute("danhSachBenhAn", danhSachBenhAn);
				List<String> columnBenhAn = new ArrayList<>();
				columnBenhAn.add("Bệnh nhân");
				columnBenhAn.add("Bác sĩ");
				columnBenhAn.add("Ngày khám");
				columnBenhAn.add("Chuẩn đoán");
				
				model.addAttribute("columnTitleScnd", columnBenhAn);
			}
			
			else model.addAttribute("danhSachBenhAn", null);
		}else {
			model.addAttribute("ketQuaTimKiem", "0");
			model.addAttribute("titlePanelInfo", "Không tìm thấy");
		}
		model.addAttribute("pageTitle", "Kết quả tìm kiếm");
		model.addAttribute("benhAn", new BenhAn());
		return "/nhan-vien/index";
	}
	
	@PostMapping("/them-benh-nhan")
	public String formThemBenhNhan(@ModelAttribute("benhNhan") BenhNhan benhNhan, Model model) {
		//TODO: process POST request
		String hoTen = benhNhan.getHoTen();
		String sdt = benhNhan.getSdt();
		boolean gioiTinh = benhNhan.getGioiTinh();
		String diaChi = benhNhan.getDiaChi();
		benhNhanRepository.save(new BenhNhan(diaChi, gioiTinh, hoTen, sdt));
		model.addAttribute("titlePanelInfo", "Tạo hồ sơ thành công");
		model.addAttribute("columnTitle", benhNhan.getTableColumnTitle());
		model.addAttribute("danhSachBenhNhan", benhNhan);
		return "/nhan-vien/index";
	}
	
	
	public static Account getUser() {
		return user;
	}
	
}
