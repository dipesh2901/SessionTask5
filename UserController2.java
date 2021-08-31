package com.neosoft.springboot.controller;

import java.util.Collections;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.neosoft.springboot.bean.User;
import com.neosoft.springboot.dao.UserRepository;

@RestController
public class UserController2 {

	@Autowired
	// private UserService userService;
	private UserRepository userRepository;

	@GetMapping("/AllUser")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/AllUserSortByUname")
	public List<User> getAllUsersSortByName() {
		List<User> sortedUsers = userRepository.findAll();
		Collections.sort(sortedUsers, (u1, u2) -> u1.getUname().compareTo(u2.getUname()));
		return sortedUsers;
	}

	@GetMapping("/AllUserSortByUpass")
	public List<User> getAllUsersSortByPass() {
		List<User> sortedUsers = userRepository.findAll();
		Collections.sort(sortedUsers, (u1, u2) -> u1.getUpass().compareTo(u2.getUname()));
		return sortedUsers;
	}

	@GetMapping("/users/byUname/{uName}")
	public User getUserByUserName(@Valid @PathVariable String uName) {
		List<User> getAllUsers = userRepository.findAll();
		User user2 = getAllUsers.stream().filter(user -> user.getUname().equals(uName)).findFirst().get();
		return user2;
	}

}
