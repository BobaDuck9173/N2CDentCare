package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.N2CDentCare.model.BenhNhan;
import com.example.N2CDentCare.model.Doctor;
import java.util.List;


public interface BenhNhanRepository extends JpaRepository<BenhNhan, String>{
	
	@Override
	List<BenhNhan> findAll();
	
	List<BenhNhan> findBySdt(String sdt);
}
