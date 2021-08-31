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

import com.neosoft.springboot.bean.Comment;
import com.neosoft.springboot.bean.Developer;

import com.neosoft.springboot.dao.DeveloperRepository;

import com.neosoft.springboot.dao.ProjectRepository;
import com.neosoft.springboot.myException.ResourceNotFoundException;

@RestController
public class DeveloperController {

	@Autowired
	private DeveloperRepository developerRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@GetMapping("/developers")
	public List<Developer> getAllDevelopers() {
		return developerRepository.findAll();
	}

	@PostMapping("/projects/{projectId}/developers")
	public Developer createDeveloper(@PathVariable Long projectId, @Valid @RequestBody Developer developer){
		return projectRepository.findById(projectId).map(project -> {
			developer.setProject(project);
			return developerRepository.save(developer);
		}).orElseThrow(() -> new com.neosoft.springboot.myException.ResourceNotFoundException("Project", "ProjectId", projectId));
	}
	
	@GetMapping("/projects/{projectId}/developers")
	public List<Developer> getAllDevelopersByProjectId(@PathVariable Long projectId){
		return developerRepository.findByProjectId(projectId);
	}
	
	@PutMapping("/project/{projectId}/developers/{id}")
	public Developer updateDeveloper(@PathVariable Long projectId, @PathVariable Long id,
			 			@Valid @RequestBody Developer developerReq) {
		
		if(!projectRepository.existsById(projectId))
			throw new com.neosoft.springboot.myException.ResourceNotFoundException("Project", "ProjectId", projectId);
		
		return developerRepository.findById(id).map(developer -> {
			developer.setDeveloperName(developerReq.getDeveloperName());
			return developerRepository.save(developer);
		}).orElseThrow(() -> new com.neosoft.springboot.myException.ResourceNotFoundException("Developer", "DeveloperId", id));
	}
	
	@DeleteMapping("/developers/{id}")
	public void deleteComment(@PathVariable Long id) {
		
		developerRepository.findById(id).map(developer -> {
			developerRepository.delete(developer);
			return developer;
		}).orElseThrow(() -> new ResourceNotFoundException("Developer", "DeveloperId", id));
	}

}
