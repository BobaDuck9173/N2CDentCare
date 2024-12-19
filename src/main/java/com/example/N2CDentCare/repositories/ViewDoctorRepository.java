package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.N2CDentCare.model.ViewDoctor;
import java.util.List;


public interface ViewDoctorRepository extends JpaRepository<ViewDoctor, String>{
	
	@Override
	List<ViewDoctor> findAll();
	
	List<ViewDoctor> findBySdt(String sdt);
	
}
