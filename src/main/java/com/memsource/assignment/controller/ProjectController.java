package com.memsource.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memsource.assignment.model.ProjectResponse;
import com.memsource.assignment.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;

    @Autowired
    ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<ProjectResponse> getAllProject(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(projectService.getAllProjects(pageNumber, pageSize));
    }


}
