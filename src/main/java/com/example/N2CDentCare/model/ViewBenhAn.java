package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "view_benh_an")
public class ViewBenhAn {
	@Id
	@Column(name = "SDT")
	private String sdt;
	
	@Column(name = "BenhNhan")
	private String BenhNhan;
	
	@Column(name = "BacSi")
	private String BacSi;
	
	@Column(name = "NgayKham")
	private String NgayKham;
	
	@Column(name = "ChuanDoan")
	private String ChuanDoan;

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getBenhNhan() {
		return BenhNhan;
	}

	public void setBenhNhan(String benhNhan) {
		BenhNhan = benhNhan;
	}

	public String getBacSi() {
		return BacSi;
	}

	public void setBacSi(String bacSi) {
		BacSi = bacSi;
	}

	public String getNgayKham() {
		return NgayKham;
	}

	public void setNgayKham(String ngayKham) {
		NgayKham = ngayKham;
	}

	public String getChuanDoan() {
		return ChuanDoan;
	}

	public void setChuanDoan(String chuanDoan) {
		ChuanDoan = chuanDoan;
	}

	public ViewBenhAn(String sdt, String benhNhan, String bacSi, String ngayKham, String chuanDoan) {
		super();
		this.sdt = sdt;
		BenhNhan = benhNhan;
		BacSi = bacSi;
		NgayKham = ngayKham;
		ChuanDoan = chuanDoan;
	}

	public ViewBenhAn() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ViewBenhAn [sdt=" + sdt + ", BenhNhan=" + BenhNhan + ", BacSi=" + BacSi + ", NgayKham=" + NgayKham
				+ ", ChuanDoan=" + ChuanDoan + "]";
	}

}
