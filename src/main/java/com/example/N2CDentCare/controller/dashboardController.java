package com.example.N2CDentCare.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class dashboardController {
	
	private static Account user;
	private static String currentPage;
	
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
		currentPage = "tongQuat";
		model.addAttribute("page", "tongQuat");
		model.addAttribute("pageTitle", "Tổng quát");
		model.addAttribute("benhNhan", new BenhNhan());
		model.addAttribute("benhNhanDelete", new BenhNhan());
		model.addAttribute("benhNhanUpdate", new BenhNhan());
		model.addAttribute("danhSachBenhAn", null);
		model.addAttribute("benhAn", new BenhAn());
		model.addAttribute("benhAnUpdate", new BenhAn());
		model.addAttribute("titlePanelInfo", "Lịch hẹn hôm nay");
		model.addAttribute("ketQuaTimKiem", null);
		return "/nhan-vien/index";
	}
	
	@GetMapping("/nhan-vien/quan-ly/benh-nhan")
	public String trangQuanLyBenhNhan(Model model){
		if (user == null) {
			return "redirect:/dang-nhap";
		}
		currentPage = "benhNhan";
		model.addAttribute("page", "benhNhan");
		model.addAttribute("pageTitle", "Quản lý bệnh nhân");
		model.addAttribute("columnTitleFrst", BenhNhan.getTableColumnTitle());
		List<BenhNhan> danhSach = benhNhanRepository.findAll();
		model.addAttribute("danhSachBenhNhan", danhSach);
		model.addAttribute("benhNhan", new BenhNhan());
		model.addAttribute("benhNhanDelete", new BenhNhan());
		model.addAttribute("benhNhanUpdate", new BenhNhan());
		model.addAttribute("danhSachBenhAn", null);
		model.addAttribute("benhAn", new BenhAn());
		model.addAttribute("benhAnUpdate", new BenhAn());
		model.addAttribute("titlePanelInfo", "Danh sách bệnh nhân");
		return "/nhan-vien/index";
	}
	
	@GetMapping("/nhan-vien/quan-ly/benh-an")
	public String trangQuanLyBenhAn(Model model){
		if (user == null) {
			return "redirect:/dang-nhap";
		}
		currentPage = "benhAn";
		model.addAttribute("page", "benhAn");
		model.addAttribute("pageTitle", "Quản lý bệnh án");
		model.addAttribute("benhNhan", new BenhNhan());
		model.addAttribute("benhNhanDelete", new BenhNhan());
		model.addAttribute("benhNhanUpdate", new BenhNhan());
		model.addAttribute("columnTitleScnd", ViewBenhAn.getTableColumnTitle());
		List<ViewBenhAn> danhSach = viewBenhAnRepository.findAll();
		model.addAttribute("danhSachBenhAn", danhSach);
		model.addAttribute("benhAn", new BenhAn());
		model.addAttribute("benhAnUpdate", new BenhAn());
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
			model.addAttribute("ketQuaTimKiem", "1");
			model.addAttribute("columnTitleFrst", BenhNhan.getTableColumnTitle());
			model.addAttribute("danhSachBenhNhan", result);

			List<ViewBenhAn> danhSachBenhAn = viewBenhAnRepository.findBySdt(benhNhan.getSdt());
			if(danhSachBenhAn.size() > 0) {
				model.addAttribute("danhSachBenhAn", danhSachBenhAn);
				model.addAttribute("columnTitleScnd", ViewBenhAn.getTableColumnTitle());
			}
			else {
				model.addAttribute("danhSachBenhAn", null);
				model.addAttribute("benhAnResult", "Chưa có bệnh án");
			}
		}else {
			model.addAttribute("ketQuaTimKiem", "0");
			model.addAttribute("danhSachBenhNhan", null);
			model.addAttribute("benhNhanResult", "Không tìm thấy");
			model.addAttribute("danhSachBenhAn", null);
			model.addAttribute("benhAnResult", "Không tìm thấy");
		}
		if(currentPage.equals("tongQuat") || currentPage.equals("benhNhan")) {
			model.addAttribute("titlePanelInfo", "Danh sách bệnh nhân");
		}
		model.addAttribute("page", currentPage);
		model.addAttribute("pageTitle", "Kết quả tìm kiếm");
		model.addAttribute("benhAn", new BenhAn());
		model.addAttribute("benhNhanUpdate", new BenhNhan());
		model.addAttribute("benhNhanDelete", new BenhNhan());
		model.addAttribute("benhAnUpdate", new BenhAn());
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
		
		if(currentPage.equals("tongQuat") || currentPage.equals("benhNhan")) {
			model.addAttribute("titlePanelInfo", "Danh sách bệnh nhân");
		}
		model.addAttribute("page", currentPage);
		model.addAttribute("titlePanelInfo", "Danh sách bệnh nhân");
		model.addAttribute("columnTitleFrst", BenhNhan.getTableColumnTitle());
		if(currentPage.equals("tongQuat")) {
			formTimBenhNhan(benhNhan, model);
		} else {
			trangQuanLyBenhNhan(model);
		}
		model.addAttribute("benhNhanResult", "Thêm thành công");
		return "/nhan-vien/index";
	}
	@PostMapping("/xoa-benh-nhan")
	public String formXoaBenhNhan(@RequestParam("MaBn") int MaBn, Model model) {
	    try {
	        if (benhNhanRepository.existsById(MaBn)) {
	            benhNhanRepository.deleteById(MaBn); 
	    	    model.addAttribute("benhNhanResult", "Xóa bệnh nhân thành công");
	        } else {
	            model.addAttribute("benhNhanResult", "Không tìm thấy bệnh nhân với ID: " + MaBn);
	        }
	    } catch (Exception e) {
	        model.addAttribute("benhNhanResult", "Bệnh nhân có bệnh án, không thể xóa");
	    }
	    
	    trangQuanLyBenhNhan(model);
	    return "/nhan-vien/index";
	}
	
	@PostMapping("/sua-benh-nhan")
	public String formSuaBenhNhan(@ModelAttribute("benhNhan") BenhNhan benhNhanUpdate, Model model) {
	    try {
	    	List<BenhNhan> updateData = benhNhanRepository.findBySdt(benhNhanUpdate.getSdtCu());
	        if (updateData.size() > 0) {
	            BenhNhan update = updateData.get(0);

	            update.setHoTen(benhNhanUpdate.getHoTen());
	            update.setSdt(benhNhanUpdate.getSdt());
	            update.setGioiTinh(benhNhanUpdate.getGioiTinh());

	            benhNhanRepository.save(update);
	        } else {
	            model.addAttribute("benhNhanResult", "Không tìm thấy bệnh nhân với SDT: " + benhNhanUpdate.getSdtCu());
	        }
	    } catch (Exception e) {
	        model.addAttribute("benhNhanResult", "Đã xảy ra lỗi khi cập nhật bệnh nhân: " + e.getMessage());
	    }

		trangQuanLyBenhNhan(model);
	    model.addAttribute("benhNhanResult", "Cập nhập hồ sơ thành công");
	    return "/nhan-vien/index";
	}
	@PostMapping("/them-benh-an")
	public String formThemBenhAn(@ModelAttribute("benhAn") BenhAn benhAn, Model model, @ModelAttribute("benhNhan") BenhNhan benhNhan) {
		//TODO: process POST request
		String sdt = benhAn.getSdt();
		String chuanDoan = benhAn.getChuanDoan();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		String ngayKham = format.format(date);
		System.out.println(ngayKham);
		String maBs = user.getId();
		benhAnRepository.save(new BenhAn(chuanDoan, maBs, ngayKham, sdt));
		
		
		model.addAttribute("page", currentPage);
		model.addAttribute("columnTitleScnd", ViewBenhAn.getTableColumnTitle());
		
		if(currentPage.equals("tongQuat")) {
			formTimBenhNhan(benhNhan, model);
		}else {
			trangQuanLyBenhAn(model);
		}
		return "/nhan-vien/index";
	}
	
	@PostMapping("/xoa-benh-an")
	public String FormxoaBenhAn( @ModelAttribute("MaBa") int MaBa, Model model, @ModelAttribute("benhNhan") BenhNhan benhNhan) {
	    try {
	        if (benhAnRepository.existsById(MaBa)) {
	            benhAnRepository.deleteById(MaBa); 
	            model.addAttribute("benhAnResult", "Xóa bệnh án thành công!");
	        } else { 
	            model.addAttribute("benhAnResult", "Không tìm thấy bệnh án với SDT: " + MaBa);
	        }
	    } catch (Exception e) {
	        model.addAttribute("benhAnResult", "Đã xảy ra lỗi khi xóa bệnh án: " + e.getMessage());
	    }

		trangQuanLyBenhAn(model);
	    return "/nhan-vien/index";
	}
	
	
	@PostMapping("/sua-benh-an")
	public String formSuaBenhAn(@ModelAttribute("benhAnUpdate") BenhAn benhAnUpdate, Model model) {
	    try {
	    	List<BenhNhan> findBenhNhan = benhNhanRepository.findBySdt(benhAnUpdate.getSdt());
	        if (findBenhNhan.size() > 0) {
	        	List<BenhAn> updateData = benhAnRepository.findByMaBa(benhAnUpdate.getMaBa());
	        	
	        	System.out.println(benhAnUpdate.getMaBa());
	        	BenhAn update = updateData.get(0);
	        		        	
	        	String str = benhAnUpdate.getNgayKham();
	        	String day = str.substring(0,2);
	        	String month = str.substring(3,5);
	        	String year = str.substring(6);

	        	update.setNgayKham(day + month + year);
	        	update.setChuanDoan(benhAnUpdate.getChuanDoan());
	        	update.setSdt(benhAnUpdate.getSdt());

	            benhAnRepository.save(update);
	            model.addAttribute("benhAnResult", "Cập nhật bệnh án thành công!");
	        } else {
	            model.addAttribute("benhAnResult", "Không tìm thấy bệnh nhân với SDT: " + benhAnUpdate.getSdt());
	        }
	    } catch (Exception e) {
	        model.addAttribute("benhAnResult", "Đã xảy ra lỗi khi cập nhật bệnh án: " + e.getMessage());
	    }

	    trangQuanLyBenhAn(model);
	    return "/nhan-vien/index"; 
	}
	public static Account getUser() {
		return user;
	}
	
	
}
