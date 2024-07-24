package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

import com.example.demo.controller.AppointmentController;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class) 
public class MyAppointmentControllerTest {
	
	private MockMvc mockMvc;
    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }
    
    @Test
    public void testGetAllAppointment() throws Exception {
        Appointment appointment1 = new Appointment();
        appointment1.setFullName("Jayesh");
        appointment1.setPhoneNumber("123456789");
        appointment1.setEmail("Jayesh@gmail.com");
        appointment1.setDiseases("cancer");
        appointment1.setDoctorName("Dr.jain");
        appointment1.setBloodGroup("A+");
        appointment1.setAppointmentTime("2:00:00");
        appointment1.setAppointmentDate("12/08/2024");
        appointment1.setStatus("completed");
         List<Appointment>  appointments = Arrays.asList(appointment1);

        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        mockMvc.perform(MockMvcRequestBuilders.get("/appointments/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Jayesh"))
                .andExpect(jsonPath("$[0].email").value("Jayesh@gmail.com"))
                .andExpect(jsonPath("$[0].phoneNumber").value("123456789"))
                .andExpect(jsonPath("$[0].appointmentDate").value("12/08/2024"))
                .andExpect(jsonPath("$[0].bloodGroup").value("A+"))
                .andExpect(jsonPath("$[0].diseases").value("cancer"))
                .andExpect(jsonPath("$[0].appointmentTime").value("2:00:00"))
                .andExpect(jsonPath("$[0].doctorName").value("Dr.jain"))
                .andExpect(jsonPath("$[0].status").value("completed"));  
    }
    @Test
    public void testGetAppointmentByName() throws Exception {
        String name = "Jayesh";
        Appointment appointment1 = new Appointment();
        appointment1.setFullName("Jayesh");
        appointment1.setPhoneNumber("123456789");
        appointment1.setEmail("Jayesh@gmail.com");
        appointment1.setDiseases("cancer");
        appointment1.setDoctorName("Dr.jain");
        appointment1.setBloodGroup("A+");
        appointment1.setAppointmentTime("2:00:00");
        appointment1.setAppointmentDate("12/08/2024");
        appointment1.setStatus("completed"); 
        List<Appointment> Appointments = Arrays.asList(appointment1);


        when(appointmentService.getByName(name)).thenReturn(Appointments);

        mockMvc.perform(MockMvcRequestBuilders.get("/appointments/appoint/{name}", name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Jayesh"))
                .andExpect(jsonPath("$[0].email").value("Jayesh@gmail.com"))
                .andExpect(jsonPath("$[0].phoneNumber").value("123456789"))
                .andExpect(jsonPath("$[0].appointmentDate").value("12/08/2024"))
                .andExpect(jsonPath("$[0].bloodGroup").value("A+"))
                .andExpect(jsonPath("$[0].diseases").value("cancer"))
                .andExpect(jsonPath("$[0].appointmentTime").value("2:00:00"))
                .andExpect(jsonPath("$[0].doctorName").value("Dr.jain"))
                .andExpect(jsonPath("$[0].status").value("completed"));  
            
    }
    @Test
    public void testCreateappointment() throws Exception {
        // Mocking the service method to do nothing
        doNothing().when(appointmentService).saveAppointment(any(Appointment.class));       
        Appointment appointment1 = new Appointment();
        appointment1.setFullName("Jayesh");
        appointment1.setPhoneNumber("123456789");
        appointment1.setEmail("Jayesh@gmail.com");
        appointment1.setDiseases("cancer");
        appointment1.setDoctorName("Dr.jain");
        appointment1.setBloodGroup("A+");
        appointment1.setAppointmentTime("2:00:00");
        appointment1.setAppointmentDate("12/08/2024");
        appointment1.setStatus("completed"); 
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAppointment = objectMapper.writeValueAsString(appointment1);

     

        // Perform POST request to create a doctor
        mockMvc.perform(post("/appointments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAppointment))
                 .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(content().string("succesfull"));  // Expect response content to be "Succesfull"
    }
    @Test
    public void testGetAppointmentById() throws Exception {
        Long id = 1L;
        Appointment appointment1 = new Appointment();
        appointment1.setId(id);
        appointment1.setFullName("Jayesh");
        appointment1.setPhoneNumber("123456789");
        appointment1.setEmail("Jayesh@gmail.com");
        appointment1.setDiseases("cancer");
        appointment1.setDoctorName("Dr.jain");
        appointment1.setBloodGroup("A+");
        appointment1.setAppointmentTime("2:00:00");
        appointment1.setAppointmentDate("12/08/2024");
        appointment1.setStatus("completed"); 
   
        when(appointmentService.getAppointmentById(id)).thenReturn(Optional.of(appointment1));

        mockMvc.perform(MockMvcRequestBuilders.get("/appointments/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Jayesh"))
                .andExpect(jsonPath("$.email").value("Jayesh@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("123456789"))
                .andExpect(jsonPath("$.appointmentDate").value("12/08/2024"))
                .andExpect(jsonPath("$.bloodGroup").value("A+"))
                .andExpect(jsonPath("$.diseases").value("cancer"))
                .andExpect(jsonPath("$.appointmentTime").value("2:00:00"))
                .andExpect(jsonPath("$.doctorName").value("Dr.jain"))
                .andExpect(jsonPath("$.status").value("completed"));

    }
    @Test
    public void testDeleteAppointment() throws Exception {
        Long appointmentId = 1L;
        doNothing().when(appointmentService).deleteAppointment(appointmentId);
        mockMvc.perform(MockMvcRequestBuilders.delete("/appointments/delete/{id}", appointmentId))
                .andExpect(status().isOk());
        }
    @Test
    public void testUpdateAppointmentStatus() throws Exception {
        Long appointmentId = 1L;
        String status = "completed";
        String found ="Updated";

     when(appointmentService.updateAppointmentStatus(appointmentId, status)).thenReturn(found);

        // Perform the PUT request to /update/{id}
        mockMvc.perform(put("/appointments/update/{id}", appointmentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(status)
                )
                .andExpect(status().isOk());
        assertEquals("Updated",found );
       }
}
