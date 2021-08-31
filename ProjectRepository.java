package com.neosoft.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.neosoft.springboot.bean.Project;

public interface ProjectRepository  extends JpaRepository<Project, Long> {

}
 

