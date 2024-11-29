package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.model.Khoa;

import java.util.List;

public interface KhoaRepository extends JpaRepository<Khoa, String>{
	
	@Override
	List<Khoa> findAll();
	
}
