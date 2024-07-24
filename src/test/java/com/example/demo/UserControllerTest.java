package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;
   

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
     
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User(1L, "Jayesh", "Jayesh@gmail.com", "Indore", "12345", "1234567890");
         List<User> users = Arrays.asList(user1);

        when(userService.getAllUser()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/User/AllUser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jayesh"))
                .andExpect(jsonPath("$[0].email").value("Jayesh@gmail.com"))
                .andExpect(jsonPath("$[0].city").value("Indore"))
                .andExpect(jsonPath("$[0].password").value("12345"))
                .andExpect(jsonPath("$[0].mobileNo").value("1234567890"));             
    }

    @Test
    public void testGetUserById() throws Exception {
        String name = "Jayesh";
        Long id =(long) 1;
        User user = new User(id, "Jayesh", "Jayesh@gmail.com", "New York", "12345", "1234567890");

        when(userService.getUserByName(name)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/User/{name}", name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jayesh"))
                .andExpect(jsonPath("$.email").value("Jayesh@gmail.com"))
                .andExpect(jsonPath("$.city").value("New York"))
        .andExpect(jsonPath("$.password").value("12345"))
        .andExpect(jsonPath("$.mobileNo").value("1234567890"));
    }
    

    @Test
    public void testCreateUser() throws Exception {
        User user = new User(1L, "John Doe", "john.doe@example.com", "New York", "password", "1234567890");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonuser = objectMapper.writeValueAsString(user);

        // Mocking void method saveUser
        doNothing().when(userService).saveuser(any(User.class));

        mockMvc.perform(post("/User/AddUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonuser))
 
                .andExpect(content().string("Succesfull"));
    }
    // Add more test cases for updateUser and deleteUser methods as needed

    

}
