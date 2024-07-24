package com.example.demo.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "appointmentDate")
    private String appointmentDate;

    @Column(name = "bloodGroup")
    private String bloodGroup;

    @Column(name = "diseases", columnDefinition = "TEXT")
    private String diseases;

    @Column(name = "appointmentTime")
    private String appointmentTime;

    @Column(name = "DoctorName")
    private String doctorName;
    
    @Column(name="status")
    private String status;

    // Constructors, getters, and setters

    public Appointment() {
    }

    public Appointment(Long id, String fullName, String email, String phoneNumber, String appointmentDate,
			String bloodGroup, String diseases, String appointmentTime, String doctorName,String status) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.appointmentDate = appointmentDate;
		this.bloodGroup = bloodGroup;
		this.diseases = diseases;
		this.appointmentTime = appointmentTime;
		this.doctorName = doctorName;
		this.status=status;
	}

	public Appointment(long l, String string, String string2, String string3, LocalDate of, String string4,
			String string5, LocalTime of2, String string6, String string7) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", appointmentDate=" + appointmentDate + ", bloodGroup=" + bloodGroup + ", diseases=" + diseases
				+ ", appointmentTime=" + appointmentTime + ", doctorName=" + doctorName + ",status="+status+"]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getDiseases() {
		return diseases;
	}

	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	// Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  
}

   
