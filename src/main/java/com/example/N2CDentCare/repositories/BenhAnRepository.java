package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.N2CDentCare.model.BenhAn;

import java.util.List;

public interface BenhAnRepository extends JpaRepository<BenhAn, Integer>{
	
	@Override
	List<BenhAn> findAll();
	
	List<BenhAn> findBySdt(String sdt);
}
