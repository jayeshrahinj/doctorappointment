package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    // Endpoint to create a new appointment
    @PostMapping("/add")
    public String createAppointment(@RequestBody Appointment appointment) {
       appointmentService.saveAppointment(appointment);
        return "succesfull";
    }

    // Endpoint to get all appointments
    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
    	return appointmentService.getAllAppointments();
        
    }
    @GetMapping("/appoint/{name}")
    public List<Appointment> getAppointmentByName(@PathVariable String name) {
        return appointmentService.getByName(name);
    }
    // Endpoint to get appointment by id
    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment;
    }

    // Endpoint to delete appointment by id
    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "Deleted";
    }
    @PutMapping("/update/{id}")
    public String updateAppointmentStatus(@PathVariable Long id, @RequestBody String status) {
    	System.out.println(status);
        return appointmentService.updateAppointmentStatus(id, status);
    }
}
