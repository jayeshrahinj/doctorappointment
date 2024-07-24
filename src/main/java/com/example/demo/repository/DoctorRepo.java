package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Doctor;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

	Doctor findByName(String name);
}