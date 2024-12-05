package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "benhnhan")
public class BenhNhan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaBn", columnDefinition = "AUTO_INCREMENT")
	private int MaBn;
	
	@Column(name = "HoTen")
	private String HoTen;
	
	@Column(name = "GioiTinh")
	private boolean GioiTinh;
	
	@Column(name = "sdt")
	private String sdt;
	
	@Column(name = "DiaChi")
	private String DiaChi;

	public int getMaBn() {
		return MaBn;
	}

	public void setMaBn(int maBn) {
		MaBn = maBn;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public boolean getGioiTinh() {
		return GioiTinh;
	}

	public String getGioiTinhAsView() {
		if (GioiTinh)
			return "Nữ";
		return "Nam";
	}

	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public BenhNhan(String hoTen, boolean gioiTinh, String sdt, String diaChi) {
		super();
		HoTen = hoTen;
		GioiTinh = gioiTinh;
		this.sdt = sdt;
		DiaChi = diaChi;
	}

	public BenhNhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BenhNhan [MaBn=" + MaBn + ", HoTen=" + HoTen + ", GioiTinh=" + getGioiTinhAsView() + ", sdt=" + sdt + ", DiaChi=" + DiaChi + "]";
	}
	
}
