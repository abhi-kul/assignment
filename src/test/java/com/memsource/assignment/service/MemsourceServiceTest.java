package com.memsource.assignment.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.memsource.assignment.model.MemsourceAuthResponse;
import com.memsource.assignment.model.MemsourceUser;
import com.memsource.assignment.repository.MemsourceUserRepository;

@RunWith(MockitoJUnitRunner.class)
public class MemsourceServiceTest {

    @Mock
    RestTemplate restTemplate;
    @Mock
    MemsourceUserRepository repository;
    @Mock
    PasswordEncoder encoder;

    @InjectMocks
    MemsourceService memsourceService;

    @Test(expected = NullPointerException.class)
    public void createMemsourceUserNullException() {
        memsourceService.createMemsourceUser(null);
    }

    @Test
    public void createMemsourceUser() {
        MemsourceUser memsourceUser = new MemsourceUser();
        Mockito.when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(MemsourceAuthResponse.class)))
                .thenReturn(generateResponseEntityObject(new MemsourceAuthResponse(), HttpStatus.OK));
        Mockito.when(repository.save(any())).thenReturn(memsourceUser);
        Mockito.when(encoder.encode(any())).thenReturn("pass");
        MemsourceUser saved = memsourceService.createMemsourceUser(memsourceUser);
        Assert.assertNotNull(saved);
        Assert.assertEquals("pass", saved.getPassword());
    }

    private ResponseEntity<MemsourceAuthResponse> generateResponseEntityObject(MemsourceAuthResponse response, HttpStatus httpStatus){
        return new ResponseEntity<>(response, httpStatus);
    }

}