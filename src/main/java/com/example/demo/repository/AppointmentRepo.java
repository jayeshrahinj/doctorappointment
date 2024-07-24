package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Appointment;
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

	List<Appointment> findByfullName(String name);
    // You can define custom query methods here if needed
	
	
}
