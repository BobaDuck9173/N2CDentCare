package com.example.N2CDentCare.controller;

import java.util.ArrayList;
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
		List<BangGiaRangSu> top8 = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			if(list.get(i).getType().equals("1"))
				top8.add(list.get(i));
		}
		for (int i = 0; i < top8.size(); i++) {
			BangGiaRangSu bgrs = top8.get(i);
			String formatGia = vndFormat(bgrs.getGia());
			bgrs.setGia(formatGia);
			top8.set(i, bgrs);
		}
		model.addAttribute("banggia", top8);
		return "/dich-vu/boc-rang-su";
	}
	
	@GetMapping("/dich-vu/bang-gia-boc-rang-su")
	public String bangGiaBocRangSu(Model model) {
		List<BangGiaRangSu> list = bangGiaRangSuRepository.findAll();
		for (int i = 0; i < list.size(); i++) {
			BangGiaRangSu bgrs = list.get(i);
			String formatGia = vndFormat(bgrs.getGia());
			bgrs.setGia(formatGia);
			list.set(i, bgrs);
		}
		model.addAttribute("banggia", list);
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
	
	private String vndFormat(String text)
    {
        String ketQua = "";
        char[] splitText = text.toCharArray();
        for (int i = 0; i < splitText.length; i++) {
        	if (splitText[i] < 48 || splitText[i] >57)
        		return text;
        }
        switch (text.length())
        {
            case 0: break;
            case 4: //1000 -> 1 000
                {
                    ketQua = String.valueOf(splitText[0]) + '.' + String.valueOf(splitText[1]) + String.valueOf(splitText[2]) + String.valueOf(splitText[3]) + " VND";
                    break;
                }
            case 5: //10000 -> 10 000
                {
                    ketQua = String.valueOf(splitText[0]) + String.valueOf(splitText[1]) + '.' + String.valueOf(splitText[2]) + String.valueOf(splitText[3]) + String.valueOf(splitText[4]) + " VND";
                    break;
                }
                case 6: // 100000 -> 100.000
                    {
                        ketQua = String.valueOf(splitText[0]) + String.valueOf(splitText[1]) + String.valueOf(splitText[2]) + '.' + String.valueOf(splitText[3]) + String.valueOf(splitText[4]) + String.valueOf(splitText[5]) + " VND";
                        break;
                    }
                case 7: // 1000000 -> 1.000.000
                    {
                        ketQua = String.valueOf(splitText[0]) + '.' + String.valueOf(splitText[1]) + String.valueOf(splitText[2]) + String.valueOf(splitText[3]) + '.' + String.valueOf(splitText[4]) + String.valueOf(splitText[5]) + String.valueOf(splitText[6]) + " VND";
                        break;
                    }
                case 8: // 10000000 -> 10.000.000
                    {
                        ketQua = String.valueOf(splitText[0]) + String.valueOf(splitText[1]) + '.' + String.valueOf(splitText[2]) + String.valueOf(splitText[3]) + String.valueOf(splitText[4]) + '.' + String.valueOf(splitText[5]) + String.valueOf(splitText[6]) + String.valueOf(splitText[7]) + " VND";
                        break;
                    }
                case 9: // 100000000 -> 100.000.000
                    {
                        ketQua = String.valueOf(splitText[0]) + String.valueOf(splitText[1]) + String.valueOf(splitText[2]) + '.' + String.valueOf(splitText[3]) + String.valueOf(splitText[4]) + String.valueOf(splitText[5]) + '.' + String.valueOf(splitText[6]) + String.valueOf(splitText[7]) + String.valueOf(splitText[8]) + " VND";
                        break;
                    }
                case 10: // 1000000000 -> 1.000.000.000
                    {
                        ketQua = String.valueOf(splitText[0]) + '.' + String.valueOf(splitText[1]) + String.valueOf(splitText[2]) + String.valueOf(splitText[3]) + '.' + String.valueOf(splitText[4]) + String.valueOf(splitText[5]) + String.valueOf(splitText[6]) + '.' + String.valueOf(splitText[7]) + String.valueOf(splitText[8]) + String.valueOf(splitText[9]) + " VND";
                        break;
                    }
                case 11: // 10000000000 -> 10.000.000.000
                    {
                        ketQua = String.valueOf(splitText[0]) + String.valueOf(splitText[1]) + '.' + String.valueOf(splitText[2]) + String.valueOf(splitText[3]) + String.valueOf(splitText[4]) + '.' + String.valueOf(splitText[5]) + String.valueOf(splitText[6]) + String.valueOf(splitText[7]) + '.' + String.valueOf(splitText[8]) + String.valueOf(splitText[9]) + String.valueOf(splitText[10]) + " VND";
                        break;
                    }
                case 12: // 100000000000 -> 100.000.000.000
                    {
                        ketQua = String.valueOf(splitText[0]) + String.valueOf(splitText[1]) + String.valueOf(splitText[2]) + '.' + String.valueOf(splitText[3]) + String.valueOf(splitText[4]) + String.valueOf(splitText[5]) + '.' + String.valueOf(splitText[6]) + String.valueOf(splitText[7]) + String.valueOf(splitText[8]) + '.' + String.valueOf(splitText[9]) + String.valueOf(splitText[10]) + String.valueOf(splitText[11]) + " VND";
                        break;
                    }

        }
        return ketQua;
    }

	
}
