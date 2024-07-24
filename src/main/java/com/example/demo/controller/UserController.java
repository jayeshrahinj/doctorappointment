package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
	UserService userservice;
	
	@GetMapping("/AllUser")
    public List<User> getAllUser() {
        return userservice.getAllUser();
    }
	 @GetMapping("/{name}")
	    public User getUserByName(@PathVariable String name) {
	      return userservice.getUserByName(name);
	    }
	 @PostMapping("/AddUser")
	    public String createUser(@RequestBody User user) {
	        userservice.saveuser(user);
	        return "Succesfull";// Save the doctor to the database
	    }

}
