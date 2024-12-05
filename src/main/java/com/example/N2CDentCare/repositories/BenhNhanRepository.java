package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.N2CDentCare.model.BenhNhan;
import com.example.N2CDentCare.model.Doctor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BenhNhanRepository extends JpaRepository<BenhNhan, Integer>{	
	@Override
	List<BenhNhan> findAll();
	
	List<BenhNhan> findBySdt(String sdt);
}
