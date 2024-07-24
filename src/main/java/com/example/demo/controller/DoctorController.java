package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Doctor;
import com.example.demo.service.DoctorService;

@RestController
@RequestMapping("/Doctor")

public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/AddDoctor")
    public String createDoctor(@RequestBody Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "Succesfull";// Save the doctor to the database
    }

    @GetMapping("/AllDoctor/")
   
    public List<Doctor> getAllDoctor() {
        return doctorService.getAllDoctor();
    }
    @GetMapping("/{name}")
    public Doctor getDoctorByName(@PathVariable String name) {
        return doctorService.getDoctorByName(name);
    }
}