package com.neosoft.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.neosoft.springboot.bean.User;

@Service
public class UserService {
	private List<User> users = new ArrayList<User>(Arrays.asList(new User(101, "Dipesh", "Dhirajlal"),
			new User(102, "Krishnaben", "Khakhrawala"), new User(103, "Aeshaben", "Bhavsar"),
			new User(104, "Mayuresh", "Rewale")));

	public List<User> getUsers() {
		return users;
	}
	
	 public User getUserByUName(String uname) {
			return users.stream().filter(u -> u.getUname().equals(uname)).findFirst().get();
		}
	
	public List<User> sortByName() {
		List<User> sortedUsers = users;
		Collections.sort(sortedUsers, (u1, u2) -> u1.getUname().compareTo(u2.getUname()));
		return sortedUsers;
	}
	
	public List<User> sortByPass() {
		List<User> sortedUsers = users;
		Collections.sort(sortedUsers, (u1, u2) -> u1.getUpass().compareTo(u2.getUpass()));
		return sortedUsers;
	}
	
	public User getUser(int id) {
		return users.stream().filter(user -> user.getUid() == id).findFirst().get();
	}

	public void addUser(User user) {
		this.users.add(user);
	}

	public void deleteUser(int id) {
		users.removeIf(user -> user.getUid() == id);
	}
	
	public void updateUser(User user,int id) {
		users.replaceAll(u -> {
			if (u.getUid() == id)
				u = user;
			return u;
		});
	}
	
	class UserNameCompare implements Comparator<User>{
		@Override
		public int compare(User u1, User u2) {
			return u1.getUname().compareTo(u2.getUname());
		}
	}
	
	class UserPassCompare implements Comparator<User>{
		@Override
		public int compare(User u1, User u2) {
			return u1.getUpass().compareTo(u2.getUpass());
		}
	}
}
