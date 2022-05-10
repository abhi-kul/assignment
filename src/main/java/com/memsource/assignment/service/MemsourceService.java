package com.memsource.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.memsource.assignment.model.MemsourceAuthRequest;
import com.memsource.assignment.model.MemsourceAuthResponse;
import com.memsource.assignment.model.MemsourceUser;
import com.memsource.assignment.repository.MemsourceUserRepository;

@Service
public class MemsourceService {

    private static final String AUTH_URL = "https://cloud.memsource.com/web/api2/v1/auth/login";
    private MemsourceUserRepository memsourceUserRepository;
    private PasswordEncoder encoder;
    private RestTemplate restTemplate;

    @Autowired
    MemsourceService(MemsourceUserRepository memsourceUserRepository, PasswordEncoder encoder,
                     RestTemplate restTemplate) {
        this.memsourceUserRepository = memsourceUserRepository;
        this.encoder = encoder;
        this.restTemplate = restTemplate;
    }

    public MemsourceUser createMemsourceUser(MemsourceUser memsourceUser) {
        String url = new StringBuilder().append(AUTH_URL).toString();
        HttpHeaders headers = new HttpHeaders();
        MemsourceAuthRequest memsourceAuthRequest = new MemsourceAuthRequest(memsourceUser.getUserName(), memsourceUser.getPassword());
        HttpEntity<MemsourceAuthRequest> httpEntity = new HttpEntity<>(memsourceAuthRequest, headers);
        ResponseEntity<MemsourceAuthResponse> memsourceAuthResponseResponseEntity = restTemplate.postForEntity(url, httpEntity, MemsourceAuthResponse.class);
        MemsourceAuthResponse responseEntityBody = memsourceAuthResponseResponseEntity.getBody();
        memsourceUser.setToken(responseEntityBody.getToken());
        memsourceUser.setPassword(encoder.encode(memsourceUser.getPassword()));
        MemsourceUser savedUser = memsourceUserRepository.save(memsourceUser);
        return savedUser;
    }
}
