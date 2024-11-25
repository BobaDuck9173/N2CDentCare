package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bacsi")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaBs")
	private Integer MaBs;
	
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
	
	@Column(name = "ChuyenKhoa")
	private int ChuyenKhoa;

	public Integer getMaBs() {
		return MaBs;
	}

	public void setMaBs(Integer maBs) {
		MaBs = maBs;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String isGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getNgaySinh() {
		return NgaySinh;
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

	public int getChuyenKhoa() {
		return ChuyenKhoa;
	}

	public void setChuyenKhoa(int chuyenKhoa) {
		ChuyenKhoa = chuyenKhoa;
	}

	public Doctor(Integer maBs, String hoTen, String gioiTinh, String ngaySinh, String sdt, String diaChi,
			int chuyenKhoa) {
		super();
		MaBs = maBs;
		HoTen = hoTen;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
		this.sdt = sdt;
		DiaChi = diaChi;
		ChuyenKhoa = chuyenKhoa;
	}

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Doctor [MaBs=" + MaBs + ", HoTen=" + HoTen + ", GioiTinh=" + GioiTinh + ", NgaySinh=" + NgaySinh
				+ ", sdt=" + sdt + ", DiaChi=" + DiaChi + ", ChuyenKhoa=" + ChuyenKhoa + "]";
	}

	
}
