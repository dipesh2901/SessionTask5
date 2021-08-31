package com.neosoft.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.springboot.bean.Developer;
import com.neosoft.springboot.bean.Project;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

	List<Developer> findByProjectId(Long projectId);

	//List<Developer> findByProjectId(Long projectId);


}
