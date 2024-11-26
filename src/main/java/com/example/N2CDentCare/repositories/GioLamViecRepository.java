package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.N2CDentCare.model.GioLamViec;

import java.util.List;


public interface GioLamViecRepository extends JpaRepository<GioLamViec, String>{
	
	@Override
	List<GioLamViec> findAll();
}
