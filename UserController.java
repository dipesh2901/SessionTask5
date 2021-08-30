package com.neosoft.springboot.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.springboot.bean.Student;
import com.neosoft.springboot.bean.User;
import com.neosoft.springboot.dao.UserRepository;
import com.neosoft.springboot.dao.UsersRepository;
import com.neosoft.springboot.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	// http://localhost:8080/{id}
	// @GetMapping("/user/{id}")
	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}

	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}

	// http://localhost:8080/user/102
	@PutMapping("/user/{id}")
	public void updateUser(@RequestBody User user, @PathVariable int id) {
		userService.updateUser(user, id);
	}
	
	@GetMapping("/users/sortByUname")
	public List<User> getUsersSortByName(){
		List<User> sortedUsers=getUsers();
		Collections.sort(sortedUsers,(u1,u2)->u1.getUname().compareTo(u2.getUname()));
		return userService.getUsers();
	}
	
	@GetMapping("/users/sortByUPass")
	public List<User> getUsersSortByPass(){
		List<User> sortedUsers=getUsers();
		Collections.sort(sortedUsers,(u1,u2)->u1.getUpass().compareTo(u2.getUpass()));
		return userService.getUsers();
	}
	
	
	@GetMapping("/user/byuname/{uname}")
	public User getUserByUName(@PathVariable String uname) {
		return userService.getUserByUName(uname);
	}
	
//	@GetMapping("/users/{name}")
//	public User getUserByName(@PathVariable String name) {
//		
//		return  userService.getUsers().stream().filter(user->user.getUname()==name);
//	}


//	@GetMapping("/users/sortByUname")
//	public List<User> getUsersSortByName() {
//		return userService.getUsers().stream().filter(user->user.getUname()==user.getUname()).sorted().collect(Collectors.toList());
//		
//	}
}
