package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {
	@Autowired
private UserRepo userrepo;
	public  List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}
	public void saveuser(User user) {
		userrepo.save(user);
		
	}
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		 	return userrepo.findByName(name);
	}

}
