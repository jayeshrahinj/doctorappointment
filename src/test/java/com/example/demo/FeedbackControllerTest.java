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

import com.example.demo.controller.FeedbackController;
import com.example.demo.model.Feedback;
import com.example.demo.service.FeedbackService;

@ExtendWith(MockitoExtension.class) 
public class FeedbackControllerTest {


    private MockMvc mockMvc;
    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
    }
    @Test
    public void testGetAllfeedback() throws Exception {
        Feedback feedback = new Feedback(1L, "Jayesh", "Jayesh@gmail.com", "Message here");
         List<Feedback>  feedbacks = Arrays.asList(feedback);

        when(feedbackService.ShowAllfeedback()).thenReturn(feedbacks);

        mockMvc.perform(MockMvcRequestBuilders.get("/feedback/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jayesh"))
                .andExpect(jsonPath("$[0].email").value("Jayesh@gmail.com"))
                .andExpect(jsonPath("$[0].message").value("Message here"));
               
    }
    @Test
    public void testCreateFeedback() throws Exception {
        // Mocking the service method to do nothing
        doNothing().when(feedbackService).addFeedback(any(Feedback.class));

        // Perform POST request to add feedback
        mockMvc.perform(post("/feedback/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Dr. Jayesh\",\"email\":\"jayesh@gmail.com\",\"message\":\"message here\"}"))
                .andExpect(status().isOk())  
                .andExpect(content().string("added"));  
    }


}
