package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.DoctorController;
import com.example.demo.model.Doctor;
import com.example.demo.service.DoctorService;

@ExtendWith(MockitoExtension.class) // Ensure MockitoExtension is used for JUnit 5
public class DoctorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }

    @Test
    public void testGetAllDoctors() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Jayesh");
        doctor.setDiseases("cancer");
        doctor.setImageUrl("Imgurl");
        doctor.setSpecialist("12345");
        doctor.setAvailability("1234567890");

        List<Doctor> doctors = Arrays.asList(doctor);

        when(doctorService.getAllDoctor()).thenReturn(doctors);

        mockMvc.perform(MockMvcRequestBuilders.get("/Doctor/AllDoctor/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jayesh"))
                .andExpect(jsonPath("$[0].diseases").value("cancer"))
                .andExpect(jsonPath("$[0].imageUrl").value("Imgurl"))
                .andExpect(jsonPath("$[0].specialist").value("12345"))
                .andExpect(jsonPath("$[0].availability").value("1234567890"));
    }
//
    @Test
    public void testGetDoctorByName() throws Exception {
        String name = "Jayesh";
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Jayesh");
        doctor.setDiseases("cancer");
        doctor.setImageUrl("Imgurl");
        doctor.setSpecialist("Lungs Cancer");
        doctor.setAvailability("Monday-Friday");
        doctor.setTime("2:00-4:00");

        when(doctorService.getDoctorByName(name)).thenReturn(doctor);

        mockMvc.perform(MockMvcRequestBuilders.get("/Doctor/{name}", name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jayesh"))
                .andExpect(jsonPath("$.diseases").value("cancer"))
                .andExpect(jsonPath("$.imageUrl").value("Imgurl"))
                .andExpect(jsonPath("$.specialist").value("Lungs Cancer"))
                .andExpect(jsonPath("$.availability").value("Monday-Friday"))
                .andExpect(jsonPath("$.time").value("2:00-4:00"));
                
    }
    
    @Test
    public void testCreateDoctor() throws Exception {
        // Mocking the service method to do nothing
        doNothing().when(doctorService).saveDoctor(any(Doctor.class));

        // Perform POST request to create a doctor
        mockMvc.perform(post("/Doctor/AddDoctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Dr. Jayesh\",\"diseases\":\"Diabetes\",\"imageUrl\":\"doc6.jpg\",\"specialist\":\"Cancer\",\"availability\":\"Monday, Friday\",\"time\":\"10:00 AM - 1:00 PM\"}"))
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(content().string("Succesfull"));  // Expect response content to be "Succesfull"
    }



    // Add more test cases as needed for other methods in DoctorController
}
