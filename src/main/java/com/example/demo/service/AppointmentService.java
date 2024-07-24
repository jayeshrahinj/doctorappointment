package com.example.demo.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepo appointmentRepository;

	// Method to save appointment
	public void saveAppointment(Appointment appointment) {
		 appointmentRepository.save(appointment);
	}

	// Method to get all appointments
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	// Method to get appointment by id
	public Optional<Appointment> getAppointmentById(Long id) {
		return appointmentRepository.findById(id);
	}

	// Method to delete appointment by id
	public void deleteAppointment(Long id) {
		appointmentRepository.deleteById(id);
	}

	// Method to search appointments by full name
	public List<Appointment> getByName(String name) {
		return appointmentRepository.findByfullName(name); 
	}

	// Method to update appointment status
	public String updateAppointmentStatus(Long id, String status) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
		if (optionalAppointment.isPresent()) {
			Appointment appointment = optionalAppointment.get();
			String statusValue = extractStatusFromJson(status);

			appointment.setStatus(statusValue); // Update appointment status
			System.out.println("line 53 from AppointmentRepository " + appointment);
			 appointmentRepository.save(appointment);
			 return "Updated";// Save updated appointment
		}
		return null; // Handle error if appointment with given id is not found
	}

	// Method to extract status value from JSON string
	private String extractStatusFromJson(String status) {
		try {
			JSONObject jsonObject = new JSONObject(status);
			return jsonObject.getString("status");
		} catch (JSONException e) {
			e.printStackTrace(); // Log the exception for debugging purposes
			return null; // Return null or handle error as appropriate
		}
	}
}
