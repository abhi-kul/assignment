package com.memsource.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memsource.assignment.model.MemsourceUser;
import com.memsource.assignment.service.MemsourceService;

@RestController
@RequestMapping("/memsourceuser")
public class MemsourceController {


    private MemsourceService memsourceService;

    @Autowired
    MemsourceController(MemsourceService memsourceService) {
        this.memsourceService = memsourceService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveUser(@RequestBody MemsourceUser memsourceUser) {
        try {
            memsourceService.createMemsourceUser(memsourceUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Not created!!!");
        }
        return ResponseEntity.ok("Created");
    }

}
