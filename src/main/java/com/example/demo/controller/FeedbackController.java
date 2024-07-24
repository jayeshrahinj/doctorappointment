package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Feedback;
import com.example.demo.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	FeedbackService feedbackservice;
	
	@PostMapping("/add")
	public String AddFeedback(@RequestBody Feedback feedback)
	{
     feedbackservice.addFeedback(feedback);
      return "added";
	}
	
	@GetMapping("/all")
	public List<Feedback> ShowAllFeedback()
	{
		return feedbackservice.ShowAllfeedback();
	}

}
