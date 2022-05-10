package com.memsource.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.memsource.assignment.model.MemsourceUser;
import com.memsource.assignment.model.ProjectResponse;
import com.memsource.assignment.repository.MemsourceUserRepository;

@Service
public class ProjectService {

    private static final String PROJECT_ENDPOINT = "https://cloud.memsource.com/web/api2/v1/projects";

    RestTemplate restTemplate;
    MemsourceUserRepository memsourceUserRepository;
    PasswordEncoder encoder;

    @Autowired
    ProjectService(RestTemplate restTemplate, MemsourceUserRepository memsourceUserRepository,
                   PasswordEncoder encoder) {
        this.restTemplate = restTemplate;
        this.memsourceUserRepository = memsourceUserRepository;
        this.encoder = encoder;
    }

    public ProjectResponse getAllProjects(int pageNumber, int pageSize) {
        MemsourceUser memsourceUser = memsourceUserRepository.getByUserName("abhinavkulshreshtha23");
        return getProjectsFromMemsource(memsourceUser, pageNumber, pageSize);
    }

    public ProjectResponse getProjectsFromMemsource(MemsourceUser user, int pageNumber, int pageSize) {
        HttpHeaders headers = new HttpHeaders();
        String token = "ApiToken " + user.getToken();
        headers.add(HttpHeaders.AUTHORIZATION, token);

        String URL = PROJECT_ENDPOINT + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize;

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<ProjectResponse> response =
                restTemplate.exchange(URL, HttpMethod.GET, requestEntity, ProjectResponse.class);

        return response.getBody();
    }
}
