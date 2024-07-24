package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepo;

@Service
public class FeedbackService {
	@Autowired
	FeedbackRepo feedbackrepo;

	public void addFeedback(Feedback feedback) {
	
		feedbackrepo.save(feedback);
		
	}

	public List<Feedback> ShowAllfeedback() {
		
		return feedbackrepo.findAll();
		
	}

}
