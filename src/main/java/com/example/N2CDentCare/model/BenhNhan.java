package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "benhnhan")
public class BenhNhan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaBn")
	private String MaBn;
	
	@Column(name = "HoTen")
	private String HoTen;
	
	@Column(name = "GioiTinh")
	private String GioiTinh;
	
	@Column(name = "NgaySinh")
	private String NgaySinh;
	
	@Column(name = "sdt")
	private String sdt;
	
	@Column(name = "DiaChi")
	private String DiaChi;

	public String getMaBn() {
		return MaBn;
	}

	public void setMaBn(String maBn) {
		MaBn = maBn;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public String getGioiTinhAsView() {
		switch (GioiTinh) {
			case "0":
				return "Nam";
			case "1":
				return "Nữ";
		}
		return "Nam";
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}
	
	public String getNgaySinhAsView() {
		String date = NgaySinh.substring(0,2);
		String month = NgaySinh.substring(2,4);
		String year = NgaySinh.substring(4);
		return date + "/" + month + "/" + year;	
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
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

	public BenhNhan(String maBn, String hoTen, String gioiTinh, String ngaySinh, String sdt, String diaChi) {
		super();
		MaBn = maBn;
		HoTen = hoTen;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
		this.sdt = sdt;
		DiaChi = diaChi;
	}

	public BenhNhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BenhNhan [MaBn=" + MaBn + ", HoTen=" + HoTen + ", GioiTinh=" + GioiTinh + ", NgaySinh=" + NgaySinh
				+ ", sdt=" + sdt + ", DiaChi=" + DiaChi + "]";
	}
	
	
	
}
