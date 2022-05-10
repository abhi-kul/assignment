package com.memsource.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memsource.assignment.model.MemsourceUser;
import com.memsource.assignment.service.MemsourceService;

@SpringBootTest
@AutoConfigureMockMvc
class MemsourceControllerTest {

    @MockBean
    MemsourceService memsourceService;

    @Autowired
    private MockMvc mvc;


    @Test
    void creatUserException() throws Exception {
        Mockito.when(memsourceService.createMemsourceUser(Mockito.any()))
                .thenThrow(new RuntimeException("Not created"));
        MvcResult mvcResult = mvc.perform(
                        post("/memsourceuser/save")
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    void creatUserSuccess() throws Exception {
        MemsourceUser memsourceUser = new MemsourceUser();
        Mockito.when(memsourceService.createMemsourceUser(Mockito.any())).thenReturn(memsourceUser);
        MvcResult mvcResult = mvc.perform(
                        post("/memsourceuser/save")
                                .content(new ObjectMapper().writeValueAsString(memsourceUser))
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

}