package com.memsource.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memsource.assignment.model.User;
import com.memsource.assignment.model.UserDto;
import com.memsource.assignment.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @PostMapping(value = "/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) {
        try {
            User user = new User();
            user.setName(userDto.getUsername());
            user.setPassword(encoder.encode(userDto.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Not created!!!");
        }
        return ResponseEntity.ok("Created!!!");
    }
}
