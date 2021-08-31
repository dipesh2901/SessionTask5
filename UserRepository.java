package com.neosoft.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.springboot.bean.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
