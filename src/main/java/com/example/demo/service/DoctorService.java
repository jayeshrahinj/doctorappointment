package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepo;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;

    public void saveDoctor(Doctor doctor) {
        doctorRepo.save(doctor); // Save the doctor to the database
    }

    public List<Doctor> getAllDoctor() {
        return doctorRepo.findAll();
    }

	public Doctor getDoctorByName(String name) {
		// TODO Auto-generated method stub
		return doctorRepo.findByName(name);
	}

}
