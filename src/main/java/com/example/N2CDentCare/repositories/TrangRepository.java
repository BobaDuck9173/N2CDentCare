package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.model.Khoa;
import com.example.N2CDentCare.model.Trang;

import java.util.List;

public interface TrangRepository extends JpaRepository<Trang, Integer>{
	
	@Override
	List<Trang> findAll();
	
}
