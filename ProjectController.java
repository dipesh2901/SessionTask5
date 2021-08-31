package com.neosoft.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.springboot.bean.Post;
import com.neosoft.springboot.bean.Project;
import com.neosoft.springboot.dao.PostRepository;
import com.neosoft.springboot.dao.ProjectRepository;
import com.neosoft.springboot.myException.ResourceNotFoundException;

@RestController
public class ProjectController {


	@Autowired
	private ProjectRepository projectRepository;
	
	@GetMapping("/project")
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	@PostMapping("/project")
	public Project createProject(@Valid @RequestBody Project project){
		return projectRepository.save(project);
	}
	
	@PutMapping("/project/{projectId}")
	public Project updateProject(@PathVariable Long projectId, @Valid @RequestBody Project projectReq) {
		
		return projectRepository.findById(projectId).map(project -> {
			project.setProjectName(projectReq.getProjectName());
			project.setContent(projectReq.getContent());
			return projectRepository.save(project);
		}).orElseThrow(() -> new com.neosoft.springboot.myException.ResourceNotFoundException("Post", "PostId", projectId));
	}
	
	@DeleteMapping("/project/{projectId}")
	public void deleteProject(@PathVariable Long projectId) {
		projectRepository.findById(projectId).map(project -> {
			projectRepository.delete(project);
			return project;
		}).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", projectId));
	}
}
